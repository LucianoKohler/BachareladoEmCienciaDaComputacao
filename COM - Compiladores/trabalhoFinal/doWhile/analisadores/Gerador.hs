module Gerador where

import AST
import Control.Monad.State
import Data.Char (toLower)

-- Funções auxiliares ----------------------------------------------------------

buscarVar :: [Var] -> Id -> Var
buscarVar [] alvoId = ("" :#: (TVoid, -1)) -- Fallback de erro
buscarVar ((idAtual :#: (tipo, offset)):restoVars) alvoId
    | idAtual == alvoId = (idAtual :#: (tipo, offset))
    | otherwise         = buscarVar restoVars alvoId

buscarFuncao :: [Funcao] -> Id -> (Tipo, Id, [Var])
buscarFuncao [] alvoId = (TVoid, alvoId, [])
buscarFuncao ((idAtual :->: (parsFormais, tipoRetorno)):restoFuns) alvoId
    | idAtual == alvoId = (tipoRetorno, idAtual, parsFormais)
    | otherwise         = buscarFuncao restoFuns alvoId

-- Cálculo de Frames ----------------------------------------------------------

calcularFrame :: [Var] -> Int -> [Var]
calcularFrame [] _ = []
calcularFrame ((nomeVar :#: (tipo, _)):restoVars) offset
    | tipo == TInt || tipo == TString = (nomeVar :#: (tipo, offset)) : calcularFrame restoVars (offset + 1)
    | otherwise                       = (nomeVar :#: (tipo, offset)) : calcularFrame restoVars (offset + 2) -- TDouble ocupa 2 slots

calcularFrames :: [Funcao] -> [(Id, [Var], Bloco)] -> ([Funcao], [(Id, [Var], Bloco)])
calcularFrames _ [] = ([], [])
calcularFrames funcoesGlobais ((nomeFuncao, varsLocais, bloco):restoCodigos) = (funcao' : funcoesGlobais', codigo' : codigos')
    where
        (tipoRetorno, _, parsFormais) = buscarFuncao funcoesGlobais nomeFuncao
        varsCalculadas = calcularFrame (parsFormais ++ varsLocais) 0
        
        parsFormais' = take (length parsFormais) varsCalculadas
        varsLocais' = drop (length parsFormais) varsCalculadas
        
        funcao' = nomeFuncao :->: (parsFormais', tipoRetorno)
        codigo' = (nomeFuncao, varsLocais', bloco)
        
        (funcoesGlobais', codigos') = calcularFrames funcoesGlobais restoCodigos

calcularLimitLocals :: [Var] -> Int
calcularLimitLocals [] = 0
calcularLimitLocals vars = 
    let (_ :#: (tipo, offset)) = last vars
    in if tipo == TDouble then offset + 2 else offset + 1

-- Gerador de rótulos (L0, L1, L2...)
novoLabel :: State Int String
novoLabel = do
    contador <- get
    put (contador + 1)
    return ("L" ++ show contador)

-- Cabeçalhos e Infraestrutura da classe ------------------------------------

genCab :: String -> String
genCab nomeClasse = ".class public " ++ nomeClasse ++ "\n.super java/lang/Object\n\n"

genMainCab :: Int -> Int -> String
genMainCab limiteStack limiteLocais = ".method public static main([Ljava/lang/String;)V\n" ++
                                      "\t.limit stack " ++ show limiteStack ++ "\n" ++
                                      "\t.limit locals " ++ show limiteLocais ++ "\n\n"

genScanner :: String -> String
genScanner nomeClasse = 
    ".field public static read Ljava/util/Scanner;\n\n" ++
    ".method public <init>()V\n" ++
    "\t.limit stack 1\n" ++
    "\t.limit locals 1\n" ++
    "\taload_0\n" ++
    "\tinvokenonvirtual java/lang/Object/<init>()V\n" ++
    "\treturn\n" ++
    ".end method\n\n" ++
    ".method static <clinit>()V\n" ++
    "\t.limit stack 3\n" ++
    "\t.limit locals 0\n" ++
    "\tnew java/util/Scanner\n" ++
    "\tdup\n" ++
    "\tgetstatic java/lang/System/in Ljava/io/InputStream;\n" ++
    "\tinvokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V\n" ++
    "\tputstatic " ++ nomeClasse ++ "/read Ljava/util/Scanner;\n" ++
    "\treturn\n" ++
    ".end method\n\n"

-- Gerador de tipo e carregadores ---------------------------------

geraTipo :: Tipo -> String
geraTipo TInt = "I"
geraTipo TDouble = "D"
geraTipo TString = "Ljava/lang/String;"
geraTipo TVoid = "V"

geraParametro :: Var -> String
geraParametro (_ :#: (tipo, _)) = geraTipo tipo

geraInt :: Int -> String
geraInt valor
    | valor == -1 = "\ticonst_m1\n"
    | 0 <= valor && valor <= 5 = "\ticonst_" ++ show valor ++ "\n"
    | -128 <= valor && valor <= 127 = "\tbipush " ++ show valor ++ "\n"
    | -32768 <= valor && valor <= 32767 = "\tsipush " ++ show valor ++ "\n"
    | otherwise = "\tldc " ++ show valor ++ "\n"

geraDouble :: Double -> String
geraDouble valor
    | valor == 0.0  = "\tdconst_0\n"
    | valor == 1.0  = "\tdconst_1\n"
    | otherwise = "\tldc2_w " ++ show valor ++ "\n"

geraOp :: Tipo -> String -> String
geraOp TInt op = "\ti" ++ op ++ "\n"
geraOp TDouble op = "\td" ++ op ++ "\n"
geraOp _ op = "\ta" ++ op ++ "\n"

-- Tradutor de expressões aritméticas -------------------------------------

geraExprA :: String -> [Var] -> [Funcao] -> Expr -> Expr -> String -> State Int (Tipo, String)
geraExprA classe tabelaVars tabelaFuns expr1 expr2 operacao = do
    (tipo1, expr1') <- genExpr classe tabelaVars tabelaFuns expr1
    (_, expr2') <- genExpr classe tabelaVars tabelaFuns expr2
    return (tipo1, expr1' ++ expr2' ++ geraOp tipo1 operacao)

genExpr :: String -> [Var] -> [Funcao] -> Expr -> State Int (Tipo, String)
genExpr _ _ _ (Const (CInt valor)) = return (TInt, geraInt valor)
genExpr _ _ _ (Const (CDouble valor)) = return (TDouble, geraDouble valor)
genExpr _ _ _ (Lit texto) = return (TString, "\tldc " ++ show texto ++ "\n") -- show recoloca as aspas na string

genExpr _ tabelaVars _ (IdVar nomeVar) = return (tipo, loadCmd tipo offset ++ "\n")
  where
    (_ :#: (tipo, offset)) = buscarVar tabelaVars nomeVar
    loadCmd TInt frame = "\tiload" ++ (if frame <= 3 then "_" else " ") ++ show frame
    loadCmd TDouble frame = "\tdload" ++ (if frame <= 3 then "_" else " ") ++ show frame
    loadCmd TString frame = "\taload" ++ (if frame <= 3 then "_" else " ") ++ show frame
    loadCmd TVoid _ = ""

genExpr classe tabelaVars tabelaFuns (IntDouble expressao) = do
    (_, expressao') <- genExpr classe tabelaVars tabelaFuns expressao
    return (TDouble, expressao' ++ "\ti2d\n")

genExpr classe tabelaVars tabelaFuns (DoubleInt expressao) = do
    (_, expressao') <- genExpr classe tabelaVars tabelaFuns expressao
    return (TInt, expressao' ++ "\td2i\n")

genExpr classe tabelaVars tabelaFuns (Add expr1 expr2) = geraExprA classe tabelaVars tabelaFuns expr1 expr2 "add"
genExpr classe tabelaVars tabelaFuns (Sub expr1 expr2) = geraExprA classe tabelaVars tabelaFuns expr1 expr2 "sub"
genExpr classe tabelaVars tabelaFuns (Mul expr1 expr2) = geraExprA classe tabelaVars tabelaFuns expr1 expr2 "mul"
genExpr classe tabelaVars tabelaFuns (Div expr1 expr2) = geraExprA classe tabelaVars tabelaFuns expr1 expr2 "div"
genExpr classe tabelaVars tabelaFuns (Neg expressao) = do
    (tipo, expressao') <- genExpr classe tabelaVars tabelaFuns expressao
    let comando = if tipo == TInt then "\tineg\n" else "\tdneg\n"
    return (tipo, expressao' ++ comando)

genExpr classe tabelaVars tabelaFuns (Chamada idFuncao argumentos) = do
    let (tipoRetorno, _, parsFormais) = buscarFuncao tabelaFuns idFuncao
    let tiposParsFormais = concatMap geraParametro parsFormais
    listaArgs <- mapM (genExpr classe tabelaVars tabelaFuns) argumentos
    let codArgumentos = concatMap snd listaArgs
    return (tipoRetorno, codArgumentos ++ "\tinvokestatic " ++ classe ++ "/" ++ idFuncao ++ "(" ++ tiposParsFormais ++ ")" ++ geraTipo tipoRetorno ++ "\n")

-- Tradutor de expressões lógicas e relacionais --------------------------

geraExprL :: String -> [Var] -> [Funcao] -> String -> String -> ExprL -> State Int String
geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Rel expressao) = geraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expressao
geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Not expressao) = geraExprL classe tabelaVars tabelaFuns labelFalso labelVerdadeiro expressao

geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Or expr1 expr2) = do
    labelMeio <- novoLabel
    expr1' <- geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelMeio expr1
    expr2' <- geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr2
    return (expr1' ++ labelMeio ++ ":\n" ++ expr2')

geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (And expr1 expr2) = do
    labelMeio <- novoLabel
    expr1' <- geraExprL classe tabelaVars tabelaFuns labelMeio labelFalso expr1
    expr2' <- geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr2
    return (expr1' ++ labelMeio ++ ":\n" ++ expr2')

geraExprRel :: Tipo -> String -> String -> String
geraExprRel TInt labelVerdadeiro operacao = "\tif_icmp" ++ operacao ++ " " ++ labelVerdadeiro ++ "\n"
geraExprRel TDouble labelVerdadeiro operacao = "\tdcmpg\n\tif" ++ operacao ++ " " ++ labelVerdadeiro ++ "\n"
geraExprRel TString labelVerdadeiro operacao = "\tif_acmp" ++ operacao ++ " " ++ labelVerdadeiro ++ "\n" 
geraExprRel TVoid _ _ = ""

auxgeraExprR :: String -> [Var] -> [Funcao] -> String -> String -> Expr -> Expr -> String -> State Int String
auxgeraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr1 expr2 operacao = do
    (tipo1, expr1') <- genExpr classe tabelaVars tabelaFuns expr1
    (_, expr2') <- genExpr classe tabelaVars tabelaFuns expr2
    return (expr1' ++ expr2' ++ geraExprRel tipo1 labelVerdadeiro operacao ++ "\tgoto " ++ labelFalso ++ "\n")

geraExprR :: String -> [Var] -> [Funcao] -> String -> String -> ExprR -> State Int String
geraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Req expr1 expr2) = auxgeraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr1 expr2 "eq"
geraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Rdif expr1 expr2) = auxgeraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr1 expr2 "ne"
geraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Rgt expr1 expr2) = auxgeraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr1 expr2 "gt"
geraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Rlt expr1 expr2) = auxgeraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr1 expr2 "lt"
geraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Rge expr1 expr2) = auxgeraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr1 expr2 "ge"
geraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso (Rle expr1 expr2) = auxgeraExprR classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expr1 expr2 "le"

-- Tradutor de comandos e blocos ------------------------------------------

geraBloco :: String -> [Var] -> [Funcao] -> Bloco -> State Int String
geraBloco classe tabelaVars tabelaFuns comandos = do
    comandos' <- mapM (geraComando classe tabelaVars tabelaFuns) comandos
    return (concat comandos')

geraComando :: String -> [Var] -> [Funcao] -> Comando -> State Int String
geraComando classe tabelaVars tabelaFuns (If expressao blocoIf blocoElse) = do
    labelVerdadeiro <- novoLabel
    labelFalso <- novoLabel
    labelFim <- novoLabel
    expressao' <- geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expressao
    blocoIf' <- geraBloco classe tabelaVars tabelaFuns blocoIf
    blocoElse' <- geraBloco classe tabelaVars tabelaFuns blocoElse
    
    if null blocoElse -- Otimiza salto se não tem else
        then return (expressao' ++ labelVerdadeiro ++ ":\n" ++ blocoIf' ++ labelFalso ++ ":\n")
        else return (expressao' ++ labelVerdadeiro ++ ":\n" ++ blocoIf' ++ "\tgoto " ++ labelFim ++ "\n" ++ labelFalso ++ ":\n" ++ blocoElse' ++ labelFim ++ ":\n")

geraComando classe tabelaVars tabelaFuns (While expressao bloco) = do
    labelInicio <- novoLabel
    labelVerdadeiro <- novoLabel
    labelFalso <- novoLabel
    expressao' <- geraExprL classe tabelaVars tabelaFuns labelVerdadeiro labelFalso expressao
    bloco' <- geraBloco classe tabelaVars tabelaFuns bloco
    return (labelInicio ++ ":\n" ++ expressao' ++ labelVerdadeiro ++ ":\n" ++ bloco' ++ "\tgoto " ++ labelInicio ++ "\n" ++ labelFalso ++ ":\n")

geraComando classe tabelaVars tabelaFuns (DoWhile bloco expressao) = do
    labelInicio <- novoLabel
    labelFim    <- novoLabel
    bloco' <- geraBloco classe tabelaVars tabelaFuns bloco
    expressao' <- geraExprL classe tabelaVars tabelaFuns labelInicio labelFim expressao
    return (labelInicio ++ ":\n" ++ bloco' ++  expressao' ++ labelFim ++ ":\n")


geraComando classe tabelaVars tabelaFuns (Atrib nomeVar expressao) = do
    (tipo, expressao') <- genExpr classe tabelaVars tabelaFuns expressao
    let (_ :#: (_, offset)) = buscarVar tabelaVars nomeVar
    return (expressao' ++ cmd tipo offset ++ "\n")
  where
    cmd TInt    frame = "\tistore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TDouble frame = "\tdstore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TString frame = "\tastore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TVoid _ = ""

geraComando classe tabelaVars tabelaFuns (Leitura nomeVar) = return ("\tgetstatic " ++ classe ++ "/read Ljava/util/Scanner;\n\tinvokevirtual java/util/Scanner/" ++ cmd tipo offset ++ "\n")
  where
    (_ :#: (tipo, offset)) = buscarVar tabelaVars nomeVar
    cmd TInt    frame = "nextInt()I\n\tistore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TDouble frame = "nextDouble()D\n\tdstore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TString frame = "nextLine()Ljava/lang/String;\n\tastore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TVoid _ = ""

geraComando classe tabelaVars tabelaFuns (Imp expressao) = do
    (tipo, expressao') <- genExpr classe tabelaVars tabelaFuns expressao
    return ("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n" ++ expressao' ++ "\tinvokevirtual java/io/PrintStream/println" ++ cmd tipo ++ "\n")
  where
    cmd TInt = "(I)V"
    cmd TDouble = "(D)V"
    cmd TString = "(Ljava/lang/String;)V"
    cmd TVoid = "()V"

geraComando _ _ _ (Ret Nothing) = return "\treturn\n"
geraComando classe tabelaVars tabelaFuns (Ret (Just expressao)) = do
    (tipo, expressao') <- genExpr classe tabelaVars tabelaFuns expressao
    return (expressao' ++ "\t" ++ pre tipo ++ "return\n")
  where
    pre TInt = "i"
    pre TDouble = "d"
    pre TString = "a"
    pre TVoid = ""

geraComando classe tabelaVars tabelaFuns (Proc idFuncao argumentos) = do
    argumentos' <- mapM (genExpr classe tabelaVars tabelaFuns) argumentos
    let codArgumentos = concatMap snd argumentos'
    let (tipoRetorno, _, parsFormais) = buscarFuncao tabelaFuns idFuncao
    let tiposParsFormais = concatMap geraParametro parsFormais
    return (codArgumentos ++ "\tinvokestatic " ++ classe ++ "/" ++ idFuncao ++ "(" ++ tiposParsFormais ++ ")" ++ geraTipo tipoRetorno ++ "\n")

-- Estrutura do programa ----------------------------------------------------------

iniciaComando :: Var -> String
iniciaComando (_ :#: (TInt, offset))    = "\ticonst_0\n\tistore" ++ (if offset <= 3 then "_" else " ") ++ show offset ++ "\n"
iniciaComando (_ :#: (TDouble, offset)) = "\tdconst_0\n\tdstore" ++ (if offset <= 3 then "_" else " ") ++ show offset ++ "\n"
iniciaComando (_ :#: (TString, offset)) = "\tldc \"\"\n\tastore" ++ (if offset <= 3 then "_" else " ") ++ show offset ++ "\n"
iniciaComando _ = ""

geraFuncao :: String -> [Funcao] -> Int -> (Id, [Var], Bloco) -> State Int String
geraFuncao nomeClasse funcoesGlobais limiteStack (nomeFuncao, varsLocais, bloco) = do
    let (tipoRetorno, _, parsFormais) = buscarFuncao funcoesGlobais nomeFuncao
    let initsLocais = concatMap iniciaComando varsLocais
    bloco' <- geraBloco nomeClasse (parsFormais ++ varsLocais) funcoesGlobais bloco
    
    -- Adiciona return no final dos voids por precaução
    let retornoVoidFinal = if tipoRetorno == TVoid then "\treturn\n" else "" 
    return (".method public static " ++ nomeFuncao ++ "(" ++ concatMap geraParametro parsFormais ++ ")" ++ geraTipo tipoRetorno ++
            "\n\t.limit stack " ++ show limiteStack ++ "\n\t.limit locals " ++ show (calcularLimitLocals (parsFormais ++ varsLocais)) ++
            "\n" ++ initsLocais ++ bloco' ++ retornoVoidFinal ++ ".end method\n\n")

geraPrograma :: String -> Programa -> State Int String
geraPrograma nomeClasse (Prog funcoes codigosFuncoes varsMain blocoMain) = do
    let (funcoes', codigosFuncoes') = calcularFrames funcoes codigosFuncoes
    -- Main inicia as variáveis no frame 1, frame 0 é dos args
    let varsMain' = calcularFrame varsMain 1 
    
    let cabecalhoClasse = genCab nomeClasse
    let geradorScanner = genScanner nomeClasse
    let cabecalhoMain = genMainCab 20 (calcularLimitLocals varsMain')
    let initsVarsMain = concatMap iniciaComando varsMain'
    
    blocoMain' <- geraBloco nomeClasse varsMain' funcoes' blocoMain
    let fimMain = "\treturn\n.end method\n\n"
    
    listaCodigosFuns <- mapM (geraFuncao nomeClasse funcoes' 20) codigosFuncoes'
    let bytecodesFuncoes = concat listaCodigosFuns
    
    return (cabecalhoClasse ++ geradorScanner ++ bytecodesFuncoes ++ cabecalhoMain ++ initsVarsMain ++ blocoMain' ++ fimMain)

-- Principal
gerador :: String -> Programa -> String
gerador nomeClasse arvorePrograma = evalState (geraPrograma nomeClasse arvorePrograma) 0