module Semantico where

import AST
import qualified Lexer as L
import qualified Parser as P
import qualified Data.Map.Strict as Map
import Data.List (group, sort) -- Para checar duplicatas nas funções


-- Mônada do professor ----------------------------------------------------------

data Result a = Result (Bool, String, a) deriving Show

instance Functor Result where
  fmap f (Result (b, s, a)) = Result (b, s, f a)

instance Applicative Result where
  pure a = Result (False, "", a)
  Result (b1, s1, f) <*> Result (b2, s2, x) = Result (b1 || b2, s1 <> s2, f x)   

instance Monad Result where 
--  return a = Result (False, "", a)
  Result (b, s, a) >>= f = let Result (b', s', a') = f a in Result (b || b', s++s', a')
  
errorMsg s = Result (True, "Erro: "++s++"\n", ())

warningMsg s = Result (False, "Advertencia: "++s++"\n", ())

-- Tabela de Variáveis ----------------------------------------------------------

-- Para funções, salva: id ([parametro] e retorno)
type TabelaGlobal = Map.Map Id ([Tipo], Tipo)

-- Para variáveis declaradas dentro de uma função
type TabelaLocal = Map.Map Id Tipo 

-- Auxiliares (extratores) ------------------------------------------------------

getId :: Var -> Id
getId (id :#: _) = id

getTipo :: Var -> Tipo
getTipo (_ :#: (tipo, _)) = tipo

-- Construção da tabela global e local -------------------------------------

checaDuplicata :: [Id] -> [Id] -- ["a", "b", "a", "c"] vira [["a", "a"], ["b"], ["c"]], então tira o primeiro elemento de listas de tamanho 2+
checaDuplicata xs = [head a | a <- group (sort xs), length a > 1]

geraTabelaGlobal :: [Funcao] -> Result TabelaGlobal
geraTabelaGlobal functions = do
    mapM_ (\n -> errorMsg("Funcao redeclarada: '" ++ n ++ "'")) (checaDuplicata (ids))
    return tabela
    where
        ids = [ id | (id :->: _) <- functions ]
        tabela = Map.fromList [(id, (map getTipo params, retorno)) | (id :->: (params, retorno)) <- functions ]

geraTabelaLocal :: [Var] -> Result TabelaLocal
geraTabelaLocal variables = do
    mapM_ (\n -> errorMsg("Variavel redeclarada: '" ++ n ++ "'")) (checaDuplicata ids)
    return tabela
    where
        ids = map getId variables
        tabela = Map.fromList [ (getId v, getTipo v) | v <- variables ]
         
-- Coerções de tipos -------------------------------------

coerceExpr :: Tipo -> Tipo -> Expr -> Expr -> String -> Result (Expr, Expr, Tipo)
coerceExpr TInt    TInt    expr1 expr2 _ = return (expr1, expr2, TInt   )
coerceExpr TInt    TFloat  expr1 expr2 _ = return (IntFloat  expr1, expr2, TFloat)
coerceExpr TInt    TDouble expr1 expr2 _ = return (IntDouble expr1, expr2, TDouble) -- No pdf: Int vira double se os dois forem diferentes
coerceExpr TFloat TFloat   expr1 expr2 _ = return (expr1, expr2, TFloat)
coerceExpr TFloat TInt     expr1 expr2 _ = return (expr1, IntFloat expr2, TFloat)
coerceExpr TFloat TDouble  expr1 expr2 _ = return (FloatDouble expr1, expr2, TDouble)
coerceExpr TDouble TDouble expr1 expr2 _ = return (expr1, expr2, TDouble)
coerceExpr TDouble TFloat  expr1 expr2 _ = return (expr1, FloatDouble expr2, TDouble)
coerceExpr TDouble TInt    expr1 expr2 _ = return (expr1, IntDouble expr2, TDouble)
coerceExpr TString TString expr1 expr2 _ = return (expr1, expr2, TString)
coerceExpr tipo1 tipo2 expr1 expr2 contexto = do
    errorMsg ("Tipos incompatíveis " ++ contexto ++ ": " ++ show tipo1 ++ " e " ++ show tipo2)
    return (expr1, expr2, tipo1) 

coerceTipo :: Tipo -> Tipo -> Expr -> String -> Result Expr
coerceTipo esperado recebido expr contexto
    | esperado == recebido  = return expr
    | esperado == TDouble, recebido == TInt      = return (IntDouble expr)
    | esperado == TFloat,  recebido == TInt      = return (IntFloat expr)
    | esperado == TDouble, recebido == TFloat    = return (FloatDouble expr)
    | esperado == TInt,    recebido == TDouble = do -- Warning mas não é o fim do programa
        warningMsg ("Conversao double para int em " ++ contexto)
        return (DoubleInt expr)
    | esperado == TInt,    recebido == TFloat = do -- Warning mas não é o fim do programa
        warningMsg ("Conversao float para int em " ++ contexto)
        return (FloatInt expr)
    | otherwise = do -- Conversão inválida
        errorMsg ("Conversao invalida: " ++ contexto ++
                  ": esperado " ++ show esperado ++
                  ", recebido " ++ show recebido)
        return expr

-- Análise de expressões aritméticas --------------------------------------------

-- Cabeçalho e operações triviais
checaExpr :: TabelaGlobal -> TabelaLocal -> Expr -> Result (Expr, Tipo)
checaExpr _ _ (Const (CInt n))    = return (Const (CInt n), TInt)
checaExpr _ _ (Const (CFloat d))  = return (Const (CFloat d), TFloat)
checaExpr _ _ (Const (CDouble d)) = return (Const (CDouble d), TDouble)
checaExpr _ _ (Lit s)             = return (Lit s, TString)

-- Ve se a variavel tá na tabela local
checaExpr _ tl (IdVar id) = 
    case Map.lookup id tl of
        Just  t -> return (IdVar id, t)
        Nothing -> do
            errorMsg("Variavel nao declarada: '" ++ id ++ "'")
            return (IdVar id, TInt) -- Assume que a variável é int

-- Ve se a negação é válida
checaExpr tg tl (Neg expr) = do
    (expr', tipo) <- checaExpr tg tl expr
    case tipo of -- se t for do tipo:
        TString -> do 
            errorMsg ("Negacao aplicada em uma variavel de tipo 'string'")
            return (Neg expr', tipo)
        _       -> return (Neg expr', tipo)

-- Checa operadores aritméticos
checaExpr tg tl (Add expr1 expr2) = checaAritmetica tg tl Add "expr: '+'" expr1 expr2
checaExpr tg tl (Sub expr1 expr2) = checaAritmetica tg tl Sub "expr: '-'" expr1 expr2
checaExpr tg tl (Mul expr1 expr2) = checaAritmetica tg tl Mul "expr: '*'" expr1 expr2
checaExpr tg tl (Div expr1 expr2) = checaAritmetica tg tl Div "expr: '/'" expr1 expr2

-- Checa chamada de funções
checaExpr tg tl (Chamada id args) = 
    case Map.lookup id tg of
        Nothing -> do -- Não achou a função
            errorMsg("Funcao nao declarada: '" ++ id ++ "'")
            return (Chamada id args, TInt) -- Assume que ela retorna int
        Just (tiposParams, tipoRetorno) -> do
            if length tiposParams /= length args 
                then do
                    errorMsg("Quantidade de parametros insuficiente em '" ++ id ++ "', esperado '" ++ show (length tiposParams) ++ "', recebido '" ++ show (length args) ++ "'")
                    return (Chamada id args, tipoRetorno)
                else do
                    args' <- mapM (checaParam tg tl id) (zip3 [1..] tiposParams args)
                    return (Chamada id args', tipoRetorno)

-- Evita recursões caso o parâmetro já tenha sido analisado
checaExpr tg tl (IntDouble expr) = do
    (expr', _) <- checaExpr tg tl expr
    return (IntDouble expr', TDouble)

checaExpr tg tl (IntFloat expr) = do
    (expr', _) <- checaExpr tg tl expr
    return (IntFloat expr', TFloat)

checaExpr tg tl (DoubleFloat expr) = do
    (expr', _) <- checaExpr tg tl expr
    return (DoubleFloat expr', TFloat)

checaExpr tg tl (DoubleInt expr) = do
    (expr', _) <- checaExpr tg tl expr
    return (DoubleInt expr', TInt)
    
checaExpr tg tl (FloatInt expr) = do
    (expr', _) <- checaExpr tg tl expr
    return (FloatInt expr', TInt)
    
checaExpr tg tl (FloatDouble expr) = do
    (expr', _) <- checaExpr tg tl expr
    return (FloatDouble expr', TDouble)

-- Analisa um argumento de cada vez (auxiliar da checaExpr)
checaParam :: TabelaGlobal -> TabelaLocal -> Id -> (Int, Tipo, Expr) -> Result Expr
checaParam tg tl idFuncao (i, tipoEsperado, expr) = do
    (e', tipoRecebido) <- checaExpr tg tl expr
    coerceTipo tipoEsperado tipoRecebido e' ("parametro " ++ show i ++ " de '" ++ idFuncao ++ "'")

-- Analisa uma operação aritmética (auxiliar de checaExpr)
checaAritmetica :: TabelaGlobal -> TabelaLocal -> (Expr -> Expr -> Expr) -> String -> Expr -> Expr -> Result (Expr, Tipo)
checaAritmetica tg tl oper contexto expr1 expr2 = do
    (expr1', tipo1) <- checaExpr tg tl expr1 
    (expr2', tipo2) <- checaExpr tg tl expr2 
    case (tipo1, tipo2) of -- Strings não devem estar em operações matemáticas
        (TString, _) -> do
            errorMsg("String em operacao matematica: " ++ contexto)
            return (oper expr1' expr2', TInt)
        (_, TString) -> do
            errorMsg("String em operacao matematica: " ++ contexto)
            return (oper expr1' expr2', TInt)
        _            -> do
            (expr1_, expr2_, tipo) <- coerceExpr tipo1 tipo2 expr1' expr2' contexto 
            return (oper expr1_ expr2_, tipo)

-- Análise de expressões lógicas ------------------------------------------------

checaExprLog :: TabelaGlobal -> TabelaLocal -> ExprL -> Result ExprL
checaExprLog tg tl (And expr1 expr2) = do -- And
    expr1' <- checaExprLog tg tl expr1
    expr2' <- checaExprLog tg tl expr2
    return (And expr1' expr2')
checaExprLog tg tl (Or expr1 expr2) = do -- Or
    expr1' <- checaExprLog tg tl expr1
    expr2' <- checaExprLog tg tl expr2
    return (Or expr1' expr2')
checaExprLog tg tl (Not expr) = do -- Not
    expr' <- checaExprLog tg tl expr
    return (Not expr')
checaExprLog tg tl (Rel expr) = do -- Rel
    expr' <- checaExprRel tg tl expr
    return (Rel expr')

-- Análise de expressões Relacionais --------------------------------------------

checaExprRel :: TabelaGlobal -> TabelaLocal -> ExprR -> Result ExprR
checaExprRel tg tl expr = do
    let (oper, expr1, expr2) = decomporR expr
    (expr1', tipo1) <- checaExpr tg tl expr1
    (expr2', tipo2) <- checaExpr tg tl expr2
    case (tipo1, tipo2) of
        (TString, TString) -> return (oper expr1' expr2') -- "ab" == "ba"
        (TString, _) -> do -- "ab" < 4
            errorMsg("Operacao relacional de tipos incompativeis (string e valor numerico)")
            return (oper expr1' expr2')
        (_, TString) -> do -- "4 >= "ab"
            errorMsg("Operacao relacional de tipos incompativeis (string e valor numerico)")
            return (oper expr1' expr2')
        _           -> do -- 4 > 5 
            (expr1_, expr2_, _) <- coerceExpr tipo1 tipo2 expr1' expr2' "operacao relacional"
            return (oper expr1_ expr2_)

-- Transforma expressão em tupla (auxiliar da checaExprRel)
decomporR :: ExprR -> (Expr -> Expr -> ExprR, Expr, Expr)
decomporR (Req  e1 e2) = (Req,  e1, e2)
decomporR (Rdif e1 e2) = (Rdif, e1, e2)
decomporR (Rlt  e1 e2) = (Rlt,  e1, e2)
decomporR (Rgt  e1 e2) = (Rgt,  e1, e2)
decomporR (Rle  e1 e2) = (Rle,  e1, e2)
decomporR (Rge  e1 e2) = (Rge,  e1, e2)

-- Análise dos comandos (if, while, ...) ----------------------------------------

checaComando :: TabelaGlobal -> TabelaLocal -> Tipo -> Comando -> Result Comando

-- If
checaComando tg tl retorno (If cond blocoI blocoE) = do
    cond' <- checaExprLog tg tl cond
    blocoI' <- mapM(checaComando tg tl retorno) blocoI
    blocoE' <- mapM(checaComando tg tl retorno) blocoE
    return (If cond' blocoI' blocoE')

-- While
checaComando tg tl retorno (While cond bloco) = do
    cond' <- checaExprLog tg tl cond
    bloco' <- mapM(checaComando tg tl retorno) bloco
    return (While cond' bloco')

-- Atrib
checaComando tg tl retorno (Atrib id expr) = do
    case Map.lookup id tl of
        Nothing -> do
            errorMsg("Variavel nao declarada: '" ++ id ++ "'")
            (expr', _) <- checaExpr tg tl expr
            return (Atrib id expr')
        Just tipoVar -> do
            (expr', tipoExpr) <- checaExpr tg tl expr
            expr_ <- coerceTipo tipoVar tipoExpr expr' ("atribuicao de variavel '" ++ id ++ "'")
            return (Atrib id expr_)

-- Read
checaComando tg tl retorno (Leitura id) = do
    case Map.lookup id tl of
        Nothing -> do
            errorMsg("Variavel nao declarada: '" ++ id ++ "'")
        Just _ -> return () -- Não implementado ainda
    return (Leitura id)

-- Print
checaComando tg tl retorno (Imp expr) = do
    (expr', _) <- checaExpr tg tl expr
    return (Imp expr')

-- Retorno do tipo void 
checaComando _ _ tipoRetorno (Ret Nothing) = do
    case tipoRetorno of
        TVoid -> return () 
        _     -> errorMsg ("Retorno vazio em funcao de tipo " ++ show tipoRetorno) -- retornou nada numa função que retorna algo
    return (Ret Nothing)

-- Retorno com valor
checaComando tg tl tipoRetorno (Ret (Just retorno)) = do
    (expr', tipoExpr) <- checaExpr tg tl retorno
    case tipoExpr of
        TVoid -> do
            errorMsg("Retorno em funcao de tipo void")
            return (Ret (Just expr'))
        _     -> do
            expr_ <- coerceTipo tipoRetorno tipoExpr expr' ("retorno de funcao de tipo '" ++ show tipoRetorno ++ "'")
            return (Ret (Just expr_))

-- Chamada de função
checaComando tg tl _ (Proc id args) = do
    (chamada', _) <- checaExpr tg tl (Chamada id args)
    case chamada' of
        Chamada _ args' -> return (Proc id args')
        _               -> return (Proc id args)

-- Análise do corpo da função ---------------------------------------------------

checaCorpoFuncao :: TabelaGlobal -> (Id, [Var], Bloco) -> Result (Id, [Var], Bloco)
checaCorpoFuncao tg (id, vars, bloco) = do
    tl <- geraTabelaLocal vars
    let tipoRetorno = case Map.lookup id tg of
                        Just (_, tipo) -> tipo
                        Nothing        -> TVoid
    bloco' <- mapM (checaComando tg tl tipoRetorno) bloco
    return (id, vars, bloco')

-- Análise da Main --------------------------------------------------------------

checaMain :: TabelaGlobal -> [Var] -> Bloco -> Result ([Var], Bloco)
checaMain tg vars bloco = do
    tl <- geraTabelaLocal vars
    bloco' <- mapM (checaComando tg tl TVoid) bloco
    return (vars, bloco')

-- Análise do programa inteiro --------------------------------------------------

checaPrograma :: Programa -> Result Programa
checaPrograma (Prog funcoes corposFuncoes varsMain blocoMain) = do
    tg <- geraTabelaGlobal funcoes
    corposFuncoes' <- mapM (checaCorpoFuncao tg) corposFuncoes
    (varsMain', blocoMain') <- checaMain tg varsMain blocoMain
    return (Prog funcoes corposFuncoes' varsMain' blocoMain')

-- OUTPUT -----------------------------------------------------------------------

testSem = do
    input <- readFile "../inputs/input.j--"
    let lex = L.alexScanTokens input
    let par = P.parser lex
    let Result (temErros, logs, programa) = checaPrograma par
    putStrLn "\nANÁLISE DO ANALISADOR SEMÂNTICO:"
    putStrLn $ "\nLogs de erro: \n" ++ logs

    if not temErros
        then do
            putStrLn "Programa anotado: " 
            print programa
        else do
            putStrLn "Erros encontrados, compilação terminada."