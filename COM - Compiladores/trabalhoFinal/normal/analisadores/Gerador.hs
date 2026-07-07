module Gerador where

import AST
import Control.Monad.State
import Data.Char (toLower)

-- Funções auxiliares ----------------------------------------------------------

buscarVar :: [Var] -> Id -> Var
buscarVar [] idVar = ("" :#: (TVoid, -1)) -- Fallback de erro
buscarVar ((id :#: (t, f)):xs) idVar
    | id == idVar = (id :#: (t, f))
    | otherwise   = buscarVar xs idVar

buscaFun :: [Funcao] -> Id -> (Tipo, Id, [Var])
buscaFun [] idFuncao = (TVoid, idFuncao, [])
buscaFun ((id :->: (pars, tipo)):xs) idFuncao
    | id == idFuncao = (tipo, id, pars)
    | otherwise      = buscaFun xs idFuncao

-- Cálculo de Frames ----------------------------------------------------------

calculaFrame :: [Var] -> Int -> [Var]
calculaFrame [] _ = []
calculaFrame ((id :#: (t, _)):vs) f
    | t == TInt || t == TString = (id :#: (t, f)) : calculaFrame vs (f + 1)
    | otherwise                 = (id :#: (t, f)) : calculaFrame vs (f + 2) -- TDouble ocupa 2 slots

calculaFrames :: [Funcao] -> [(Id, [Var], Bloco)] -> ([Funcao], [(Id, [Var], Bloco)])
calculaFrames _ [] = ([], [])
calculaFrames funs ((id, vars, b):cs) = (func' : funTab', cod' : codTab')
    where
        (t, _, parsFormais) = buscaFun funs id
        varsCalculadas = calculaFrame (parsFormais ++ vars) 0
        paramFormais' = take (length parsFormais) varsCalculadas
        vars' = drop (length parsFormais) varsCalculadas
        func' = id :->: (paramFormais', t)
        cod' = (id, vars', b)
        (funTab', codTab') = calculaFrames funs cs

calculaLimitLocals :: [Var] -> Int
calculaLimitLocals [] = 0
calculaLimitLocals vars = 
    let (_ :#: (t, f)) = last vars
    in if t == TDouble then f + 2 else f + 1

-- Gerador de rótulos (L0, L1, L2...)
criaLabel :: State Int String
criaLabel = do
    n <- get
    put (n + 1)
    return ("L" ++ show n)

-- Cabeçalhos e Infraestrutura da classe ------------------------------------

geraCabecalho :: String -> String
geraCabecalho nome = ".class public " ++ nome ++ "\n.super java/lang/Object\n\n"

geraCabPrincipal :: Int -> Int -> String
geraCabPrincipal s l = ".method public static main([Ljava/lang/String;)V\n" ++
                 "\t.limit stack " ++ show s ++ "\n" ++
                 "\t.limit locals " ++ show l ++ "\n\n"
-- Para o READ
geraScanner :: String -> String
geraScanner className = 
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
    "\tputstatic " ++ className ++ "/read Ljava/util/Scanner;\n" ++
    "\treturn\n" ++
    ".end method\n\n"

-- Gerador de tipo e carregadores ---------------------------------

geraTipo :: Tipo -> String
geraTipo TInt = "I"
geraTipo TDouble = "D"
geraTipo TString = "Ljava/lang/String;"
geraTipo TVoid = "V"

geraParametro :: Var -> String
geraParametro (_ :#: (t, _)) = geraTipo t

geraInt :: Int -> String
geraInt i
    | i == -1 = "\ticonst_m1\n"
    | 0 <= i && i <= 5 = "\ticonst_" ++ show i ++ "\n"
    | -128 <= i && i <= 127 = "\tbipush " ++ show i ++ "\n"
    | -32768 <= i && i <= 32767 = "\tsipush " ++ show i ++ "\n"
    | otherwise = "\tldc " ++ show i ++ "\n"

geraDouble :: Double -> String
geraDouble d
    | d == 0.0  = "\tdconst_0\n"
    | d == 1.0  = "\tdconst_1\n"
    | otherwise = "\tldc2_w " ++ show d ++ "\n"

geraOp :: Tipo -> String -> String
geraOp TInt op = "\ti" ++ op ++ "\n"
geraOp TDouble op = "\td" ++ op ++ "\n"
geraOp _ op = "\ta" ++ op ++ "\n"

-- Tradutor de expressões aritméticas -------------------------------------

geraExprA :: String -> [Var] -> [Funcao] -> Expr -> Expr -> String -> State Int (Tipo, String)
geraExprA c tab fun e1 e2 op = do
    (t1, e1') <- genExpr c tab fun e1
    (_, e2') <- genExpr c tab fun e2
    return (t1, e1' ++ e2' ++ geraOp t1 op)

genExpr :: String -> [Var] -> [Funcao] -> Expr -> State Int (Tipo, String)
genExpr _ _ _ (Const (CInt i)) = return (TInt, geraInt i)
genExpr _ _ _ (Const (CDouble d)) = return (TDouble, geraDouble d)
genExpr _ _ _ (Lit s) = return (TString, "\tldc " ++ show s ++ "\n") -- show repõe as aspas automaticamente

genExpr _ tab _ (IdVar id) = return (t, loadCmd t f ++ "\n")
  where
    (_ :#: (t, f)) = buscarVar tab id
    loadCmd TInt frame = "\tiload" ++ (if frame <= 3 then "_" else " ") ++ show frame
    loadCmd TDouble frame = "\tdload" ++ (if frame <= 3 then "_" else " ") ++ show frame
    loadCmd TString frame = "\taload" ++ (if frame <= 3 then "_" else " ") ++ show frame
    loadCmd TVoid _ = ""

genExpr c tab fun (IntDouble e) = do
    (_, e') <- genExpr c tab fun e
    return (TDouble, e' ++ "\ti2d\n")

genExpr c tab fun (DoubleInt e) = do
    (_, e') <- genExpr c tab fun e
    return (TInt, e' ++ "\td2i\n")

genExpr c tab fun (Add e1 e2) = geraExprA c tab fun e1 e2 "add"
genExpr c tab fun (Sub e1 e2) = geraExprA c tab fun e1 e2 "sub"
genExpr c tab fun (Mul e1 e2) = geraExprA c tab fun e1 e2 "mul"
genExpr c tab fun (Div e1 e2) = geraExprA c tab fun e1 e2 "div"
genExpr c tab fun (Neg e) = do
    (t, e') <- genExpr c tab fun e
    let cmd = if t == TInt then "\tineg\n" else "\tdneg\n"
    return (t, e' ++ cmd)

genExpr c tab fun (Chamada idFuncao pars) = do
    let (t, _, parsFormais) = buscaFun fun idFuncao
    let tiposParsFormais = concatMap geraParametro parsFormais
    listaPars <- mapM (genExpr c tab fun) pars
    let codPars = concatMap snd listaPars
    return (t, codPars ++ "\tinvokestatic " ++ c ++ "/" ++ idFuncao ++ "(" ++ tiposParsFormais ++ ")" ++ geraTipo t ++ "\n")

-- Tradutor de expressões lógicas e relacionais --------------------------

geraExprL :: String -> [Var] -> [Funcao] -> String -> String -> ExprL -> State Int String
geraExprL c tab fun v f (Rel e) = geraExprR c tab fun v f e
geraExprL c tab fun v f (Not e) = geraExprL c tab fun f v e
geraExprL c tab fun v f (Or e1 e2) = do
    l1 <- criaLabel
    e1' <- geraExprL c tab fun v l1 e1
    e2' <- geraExprL c tab fun v f e2
    return (e1' ++ l1 ++ ":\n" ++ e2')

geraExprL c tab fun v f (And e1 e2) = do
    l1 <- criaLabel
    e1' <- geraExprL c tab fun l1 f e1
    e2' <- geraExprL c tab fun v f e2
    return (e1' ++ l1 ++ ":\n" ++ e2')

geraExprRel :: Tipo -> String -> String -> String
geraExprRel TInt v op = "\tif_icmp" ++ op ++ " " ++ v ++ "\n"
geraExprRel TDouble v op = "\tdcmpg\n\tif" ++ op ++ " " ++ v ++ "\n"
geraExprRel TString v op = "\tif_acmp" ++ op ++ " " ++ v ++ "\n" 
geraExprRel TVoid _ _ = ""

auxgeraExprR :: String -> [Var] -> [Funcao] -> String -> String -> Expr -> Expr -> String -> State Int String
auxgeraExprR c tab fun v f e1 e2 op = do
    (t1, e1') <- genExpr c tab fun e1
    (_, e2') <- genExpr c tab fun e2
    return (e1' ++ e2' ++ geraExprRel t1 v op ++ "\tgoto " ++ f ++ "\n")

geraExprR :: String -> [Var] -> [Funcao] -> String -> String -> ExprR -> State Int String
geraExprR c tab fun v f (Req e1 e2) = auxgeraExprR c tab fun v f e1 e2 "eq"
geraExprR c tab fun v f (Rdif e1 e2) = auxgeraExprR c tab fun v f e1 e2 "ne"
geraExprR c tab fun v f (Rgt e1 e2) = auxgeraExprR c tab fun v f e1 e2 "gt"
geraExprR c tab fun v f (Rlt e1 e2) = auxgeraExprR c tab fun v f e1 e2 "lt"
geraExprR c tab fun v f (Rge e1 e2) = auxgeraExprR c tab fun v f e1 e2 "ge"
geraExprR c tab fun v f (Rle e1 e2) = auxgeraExprR c tab fun v f e1 e2 "le"

-- Tradutor de comandos e blocos ------------------------------------------

geraBloco :: String -> [Var] -> [Funcao] -> Bloco -> State Int String
geraBloco c tab fun cmds = do
    lista <- mapM (geraComando c tab fun) cmds
    return (concat lista)

geraComando :: String -> [Var] -> [Funcao] -> Comando -> State Int String
geraComando c tab fun (If e b1 b2) = do
    lv    <- criaLabel
    lf    <- criaLabel
    fim   <- criaLabel
    e'    <- geraExprL c tab fun lv lf e
    b1'   <- geraBloco c tab fun b1
    b2'   <- geraBloco c tab fun b2
    
    if null b2 -- Se não tem Else, otimiza o salto
        then return (e' ++ lv ++ ":\n" ++ b1' ++ lf ++ ":\n")
        else return (e' ++ lv ++ ":\n" ++ b1' ++ "\tgoto " ++ fim ++ "\n" ++ lf ++ ":\n" ++ b2' ++ fim ++ ":\n")

geraComando c tab fun (While e b) = do
    li <- criaLabel
    lv <- criaLabel
    lf <- criaLabel
    e' <- geraExprL c tab fun lv lf e
    b' <- geraBloco c tab fun b
    return (li ++ ":\n" ++ e' ++ lv ++ ":\n" ++ b' ++ "\tgoto " ++ li ++ "\n" ++ lf ++ ":\n")

geraComando c tab fun (Atrib id e) = do
    (t, e') <- genExpr c tab fun e
    let (_ :#: (_, frame)) = buscarVar tab id
    return (e' ++ cmd t frame ++ "\n")
  where
    cmd TInt frame = "\tistore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TDouble frame = "\tdstore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TString frame = "\tastore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TVoid _ = ""

geraComando c tab fun (Leitura id) = return ("\tgetstatic " ++ c ++ "/read Ljava/util/Scanner;\n\tinvokevirtual java/util/Scanner/" ++ cmd t f ++ "\n")
  where
    (_ :#: (t, f)) = buscarVar tab id
    cmd TInt frame = "nextInt()I\n\tistore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TDouble frame = "nextDouble()D\n\tdstore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TString frame = "nextLine()Ljava/lang/String;\n\tastore" ++ (if frame <= 3 then "_" else " ") ++ show frame
    cmd TVoid _ = ""

geraComando c tab fun (Imp e) = do
    (t, e') <- genExpr c tab fun e
    return ("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n" ++ e' ++ "\tinvokevirtual java/io/PrintStream/println" ++ cmd t ++ "\n")
  where
    cmd TInt = "(I)V"
    cmd TDouble = "(D)V"
    cmd TString = "(Ljava/lang/String;)V"
    cmd TVoid = "()V"

geraComando _ _ _ (Ret Nothing) = return "\treturn\n"
geraComando c tab fun (Ret (Just e)) = do
    (t, e') <- genExpr c tab fun e
    return (e' ++ "\t" ++ pre t ++ "return\n")
  where
    pre TInt = "i"
    pre TDouble = "d"
    pre TString = "a"
    pre TVoid = ""

geraComando c tab fun (Proc id exprs) = do
    e'xprs <- mapM (genExpr c tab fun) exprs
    let codExprs = concatMap snd e'xprs
    let (t, _, parsFormais) = buscaFun fun id
    let tiposParsFormais = concatMap geraParametro parsFormais
    return (codExprs ++ "\tinvokestatic " ++ c ++ "/" ++ id ++ "(" ++ tiposParsFormais ++ ")" ++ geraTipo t ++ "\n")

-- Estrutura do programa ----------------------------------------------------------

iniciaComando :: Var -> String
iniciaComando (_ :#: (TInt, f)) = "\ticonst_0\n\tistore" ++ (if f <= 3 then "_" else " ") ++ show f ++ "\n"
iniciaComando (_ :#: (TDouble, f)) = "\tdconst_0\n\tdstore" ++ (if f <= 3 then "_" else " ") ++ show f ++ "\n"
iniciaComando (_ :#: (TString, f)) = "\tldc \"\"\n\tastore" ++ (if f <= 3 then "_" else " ") ++ show f ++ "\n"
iniciaComando _ = ""

geraFuncao :: String -> [Funcao] -> Int -> (Id, [Var], Bloco) -> State Int String
geraFuncao c fun s (id, vars, bloco) = do
    let (t, _, parsFormais) = buscaFun fun id
    let inits = concatMap iniciaComando vars
    b' <- geraBloco c (parsFormais ++ vars) fun bloco
    -- Adicionamos um return de segurança no final das funções Void caso o usuário esqueça
    let finalReturn = if t == TVoid then "\treturn\n" else "" 
    return (".method public static " ++ id ++ "(" ++ concatMap geraParametro parsFormais ++ ")" ++ geraTipo t ++
            "\n\t.limit stack " ++ show s ++ "\n\t.limit locals " ++ show (calculaLimitLocals (parsFormais ++ vars)) ++
            "\n" ++ inits ++ b' ++ finalReturn ++ ".end method\n\n")

geraPrograma :: String -> Programa -> State Int String
geraPrograma nome (Prog fun codFun var m) = do
    let (fun', cod'Fun) = calculaFrames fun codFun
    -- Main inicia as variáveis no frame 1, pois o frame 0 já é ocupado por args
    let vars' = calculaFrame var 1 
    let cabClasse = geraCabecalho nome
    let scanner = geraScanner nome
    let cabMain = geraCabPrincipal 20 (calculaLimitLocals vars')
    let initVarsMain = concatMap iniciaComando vars'
    codMain <- geraBloco nome vars' fun' m
    let endMain = "\treturn\n.end method\n\n"
    listCodFunc <- mapM (geraFuncao nome fun' 20) cod'Fun
    let genCodFunc = concat listCodFunc
    return (cabClasse ++ scanner ++ genCodFunc ++ cabMain ++ initVarsMain ++ codMain ++ endMain)

-- Principal
gerador :: String -> Programa -> String
gerador nome p = evalState (geraPrograma nome p) 0