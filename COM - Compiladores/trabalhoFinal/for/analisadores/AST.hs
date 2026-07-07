module AST where

type Id = String

data Tipo = TInt | TDouble | TString | TVoid
    deriving ( Show , Eq )

data TCons = CDouble Double | CInt Int 
    deriving Show

data Expr 
    = Add Expr Expr 
    | Sub Expr Expr 
    | Mul Expr Expr 
    | Div Expr Expr 
    | Neg Expr 
    | Const TCons
    | IdVar String 
    | Chamada Id [ Expr ] 
    | Lit String
    | IntDouble Expr 
    | DoubleInt Expr 
        deriving Show

data ExprR -- Relacional
    = Req Expr Expr 
    | Rdif Expr Expr 
    | Rlt Expr Expr
    | Rgt Expr Expr 
    | Rle Expr Expr 
    | Rge Expr Expr
        deriving Show

data ExprL -- Lógico
    = And ExprL ExprL 
    | Or ExprL ExprL 
    | Not ExprL
    | Rel ExprR 
        deriving Show

data Var = Id :#: ( Tipo , Int ) 
    deriving Show

data Funcao = Id :->: ([ Var ], Tipo ) 
    deriving Show

data Programa = Prog [ Funcao ] [( Id , [ Var ] , Bloco )] [ Var ] Bloco
    deriving Show 

type Bloco = [ Comando ]

data Comando 
    = If ExprL Bloco Bloco
    | While ExprL Bloco
    | For Comando ExprL Comando Bloco
    | Atrib Id Expr
    | Leitura Id
    | Imp Expr
    | Ret ( Maybe Expr )
    | Proc Id [ Expr ]
        deriving Show
