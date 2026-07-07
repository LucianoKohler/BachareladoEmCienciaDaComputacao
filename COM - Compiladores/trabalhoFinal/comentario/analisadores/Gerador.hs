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

buscarFuncao :: [Funcao] -> Id -> (Tipo, Id, [Var])
buscarFuncao [] idFuncao = (TVoid, idFuncao, [])
buscarFuncao ((id :->: (pars, tipo)):xs) idFuncao
    | id == idFuncao = (tipo, id, pars)
    | otherwise      = buscarFuncao xs idFuncao

-- Cálculo de Frames ----------------------------------------------------------

calcularFrame :: [Var] -> Int -> [Var]
calcularFrame [] _ = []
calcularFrame ((id :#: (t, _)):vs) f
    | t == TInt || t == TString = (id :#: (t, f)) : calcularFrame vs (f + 1)
    | otherwise                 = (id :#: (t, f)) : calcularFrame vs (f + 2) -- TDouble ocupa 2 slots

calcularFrames :: [Funcao] -> [(Id, [Var], Bloco)] -> ([Funcao], [(Id, [Var], Bloco)])
calcularFrames _ [] = ([], [])
calcularFrames funs ((id, vars, b):cs) = (newFunc : newFunTab, newCod : newCodsTab)
    where
        (t, _, parsFormais) = buscarFuncao funs id
        varsCalculadas = calcularFrame (parsFormais ++ vars) 0
        newParsFormais = take (length parsFormais) varsCalculadas
        newVars = drop (length parsFormais) varsCalculadas
        newFunc = id :->: (newParsFormais, t)
        newCod = (id, newVars, b)
        (newFunTab, newCodsTab) = calcularFrames funs cs

calcularLimitLocals :: [Var] -> Int
calcularLimitLocals [] = 0
calcularLimitLocals vars = 
    let (_ :#: (t, f)) = last vars
    in if t == TDouble then f + 2 else f + 1

-- Gerador de rótulos (L0, L1, L2...)
novoLabel :: State Int String
novoLabel = do
    n <- get
    put (n + 1)
    return ("L" ++ show n)

-- Cabeçalhos e Infraestrutura da classe ------------------------------------

genCab :: String -> String
genCab nome = ".class public " ++ nome ++ "\n.super java/lang/Object\n\n"

genMainCab :: Int -> Int -> String
genMainCab s l = ".method public static main([Ljava/lang/String;)V\n" ++
                 "\t.limit stack " ++ show s ++ "\n" ++
                 "\t.limit locals " ++ show l ++ "\n\n"

genScanner :: String -> String
genScanner className = 
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
    (t1, newE1) <- genExpr c tab fun e1
    (_, newE2) <- genExpr c tab fun e2
    return (t1, newE1 ++ newE2 ++ geraOp t1 op)

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
    (_, newE) <- genExpr c tab fun e
    return (TDouble, newE ++ "\ti2d\n")

genExpr c tab fun (DoubleInt e) = do
    (_, newE) <- genExpr c tab fun e
    return (TInt, newE ++ "\td2i\n")

genExpr c tab fun (Add e1 e2) = geraExprA c tab fun e1 e2 "add"
genExpr c tab fun (Sub e1 e2) = geraExprA c tab fun e1 e2 "sub"
genExpr c tab fun (Mul e1 e2) = geraExprA c tab fun e1 e2 "mul"
genExpr c tab fun (Div e1 e2) = geraExprA c tab fun e1 e2 "div"
genExpr c tab fun (Neg e) = do
    (t, newE) <- genExpr c tab fun e
    let cmd = if t == TInt then "\tineg\n" else "\tdneg\n"
    return (t, newE ++ cmd)

genExpr c tab fun (Chamada idFuncao pars) = do
    let (t, _, parsFormais) = buscarFuncao fun idFuncao
    let tiposParsFormais = concatMap geraParametro parsFormais
    listaPars <- mapM (genExpr c tab fun) pars
    let codPars = concatMap snd listaPars
    return (t, codPars ++ "\tinvokestatic " ++ c ++ "/" ++ idFuncao ++ "(" ++ tiposParsFormais ++ ")" ++ geraTipo t ++ "\n")

-- Tradutor de expressões lógicas e relacionais --------------------------

geraExprL :: String -> [Var] -> [Funcao] -> String -> String -> ExprL -> State Int String
geraExprL c tab fun v f (Rel e) = geraExprR c tab fun v f e
geraExprL c tab fun v f (Not e) = geraExprL c tab fun f v e
geraExprL c tab fun v f (Or e1 e2) = do
    l1 <- novoLabel
    newE1 <- geraExprL c tab fun v l1 e1
    newE2 <- geraExprL c tab fun v f e2
    return (newE1 ++ l1 ++ ":\n" ++ newE2)

geraExprL c tab fun v f (And e1 e2) = do
    l1 <- novoLabel
    newE1 <- geraExprL c tab fun l1 f e1
    newE2 <- geraExprL c tab fun v f e2
    return (newE1 ++ l1 ++ ":\n" ++ newE2)

geraExprRel :: Tipo -> String -> String -> String
geraExprRel TInt v op = "\tif_icmp" ++ op ++ " " ++ v ++ "\n"
geraExprRel TDouble v op = "\tdcmpg\n\tif" ++ op ++ " " ++ v ++ "\n"
geraExprRel TString v op = "\tif_acmp" ++ op ++ " " ++ v ++ "\n" 
geraExprRel TVoid _ _ = ""

auxgeraExprR :: String -> [Var] -> [Funcao] -> String -> String -> Expr -> Expr -> String -> State Int String
auxgeraExprR c tab fun v f e1 e2 op = do
    (t1, newE1) <- genExpr c tab fun e1
    (_, newE2) <- genExpr c tab fun e2
    return (newE1 ++ newE2 ++ geraExprRel t1 v op ++ "\tgoto " ++ f ++ "\n")

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
    lv <- novoLabel
    lf <- novoLabel
    fim <- novoLabel
    newE <- geraExprL c tab fun lv lf e
    newB1 <- geraBloco c tab fun b1
    newB2 <- geraBloco c tab fun b2
    
    if null b2 -- Se não tem Else, otimizamos o salto
        then return (newE ++ lv ++ ":\n" ++ newB1 ++ lf ++ ":\n")
        else return (newE ++ lv ++ ":\n" ++ newB1 ++ "\tgoto " ++ fim ++ "\n" ++ lf ++ ":\n" ++ newB2 ++ fim ++ ":\n")

geraComando c tab fun (While e b) = do
    li <- novoLabel
    lv <- novoLabel
    lf <- novoLabel
    e' <- geraExprL c tab fun lv lf e
    b' <- geraBloco c tab fun b
    return (li ++ ":\n" ++ e' ++ lv ++ ":\n" ++ b' ++ "\tgoto " ++ li ++ "\n" ++ lf ++ ":\n")

geraComando c tab fun (Atrib id e) = do
    (t, newE) <- genExpr c tab fun e
    let (_ :#: (_, frame)) = buscarVar tab id
    return (newE ++ cmd t frame ++ "\n")
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
    (t, newE) <- genExpr c tab fun e
    return ("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n" ++ newE ++ "\tinvokevirtual java/io/PrintStream/println" ++ cmd t ++ "\n")
  where
    cmd TInt = "(I)V"
    cmd TDouble = "(D)V"
    cmd TString = "(Ljava/lang/String;)V"
    cmd TVoid = "()V"

geraComando _ _ _ (Ret Nothing) = return "\treturn\n"
geraComando c tab fun (Ret (Just e)) = do
    (t, newE) <- genExpr c tab fun e
    return (newE ++ "\t" ++ pre t ++ "return\n")
  where
    pre TInt = "i"
    pre TDouble = "d"
    pre TString = "a"
    pre TVoid = ""

geraComando c tab fun (Proc id exprs) = do
    newExprs <- mapM (genExpr c tab fun) exprs
    let codExprs = concatMap snd newExprs
    let (t, _, parsFormais) = buscarFuncao fun id
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
    let (t, _, parsFormais) = buscarFuncao fun id
    let inits = concatMap iniciaComando vars
    newBloco <- geraBloco c (parsFormais ++ vars) fun bloco
    -- Adicionamos um return de segurança no final das funções Void caso o usuário esqueça
    let finalReturn = if t == TVoid then "\treturn\n" else "" 
    return (".method public static " ++ id ++ "(" ++ concatMap geraParametro parsFormais ++ ")" ++ geraTipo t ++
            "\n\t.limit stack " ++ show s ++ "\n\t.limit locals " ++ show (calcularLimitLocals (parsFormais ++ vars)) ++
            "\n" ++ inits ++ newBloco ++ finalReturn ++ ".end method\n\n")

geraPrograma :: String -> Programa -> State Int String
geraPrograma nome (Prog fun codFun var m) = do
    let (newFun, newCodFun) = calcularFrames fun codFun
    -- Main inicia as variáveis no frame 1, pois o frame 0 já é ocupado por args
    let newVars = calcularFrame var 1 
    let cabClasse = genCab nome
    let scanner = genScanner nome
    let cabMain = genMainCab 20 (calcularLimitLocals newVars)
    let initVarsMain = concatMap iniciaComando newVars
    codMain <- geraBloco nome newVars newFun m
    let endMain = "\treturn\n.end method\n\n"
    listCodFunc <- mapM (geraFuncao nome newFun 20) newCodFun
    let genCodFunc = concat listCodFunc
    return (cabClasse ++ scanner ++ genCodFunc ++ cabMain ++ initVarsMain ++ codMain ++ endMain)

-- Principal
gerador :: String -> Programa -> String
gerador nome p = evalState (geraPrograma nome p) 0