-- Para compilar, rodar alex Lexer.x, happy --ghc Parser.y e por fim, ghci Parser.hs
-- Só chamar a main no GHCI daí :D

{
module Parser where

import Token
import AST
import qualified Lexer as L
}

%name parser
%tokentype { Token }
%error { parseError }
%token 
 int       { TINT }
 double    { TDOUBLE }
 string    { TSTRING }
 void      { TVOID }
 "<"       { LESS }
 "<="      { LEQ }
 ">"       { GRTR }
 ">="      { GEQ }
 "=="      { EQUAL }
 "/="      { NEQUAL }
 "&&"      { AND }
 "||"      { OR }
 "!"       { NOT }
 "+"       { ADD }
 "-"       { SUB }
 "*"       { MUL }
 "/"       { DIV }
 "{"       { LBRACE }
 "}"       { RBRACE }
 "("       { LPAR }
 ")"       { RPAR }
 ","       { COMMA }
 ";"       { SEMICOLON }
 "if"      { IF }
 "else"    { ELSE }
 "while"   { WHILE }
 "="       { ATTRIB }
 "read"    { READ }
 "print"   { PRINT }
 "return"  { RETURN }
 Id        { ID $$ }
 Int       { CINT $$ }
 Double    { CDOUBLE $$ }
 LiteralStr{ LITERAL $$ }

-- Associações para eviar ambiguidade
-- mais abaixo -> maior precedência
%left "||"
%left "&&"
%right "!"
%nonassoc "<" "<=" ">" ">=" "==" "/=" -- Dá parse error se fizer a < b < c por exemplo
%left "+" "-"
%left "*" "/"

%%

Programa -- fst $1 = cabeçalhos, snd $1 = conteúdos + corpo das funções, fst $2 = variáveis da main, snd $2 = códigos da main
  : ListaFuncoes BlocoPrincipal     { Prog (fst $1) (snd $1) (fst $2) (snd $2) }
  | BlocoPrincipal                  { Prog [] [] (fst $1) (snd $1) }

ListaFuncoes 
  : ListaFuncoes Funcao    { (fst $1 ++ [fst $2], snd $1 ++ [snd $2]) }
  | Funcao                 { ([fst $1], [snd $1]) }

Funcao --                                               Ass.Funcao       (Id  [    Var   ],  Bloco)
  : TipoRet Id "(" ParamFormais ")" BlocoPrincipal  { ($2 :->: ($4, $1), ($2, $4 ++ fst $6, snd $6)) }
  | TipoRet Id "(" ")" BlocoPrincipal               { ($2 :->: ([], $1), ($2, fst $5, snd $5)) }

TipoRet 
  : Tipo       { $1 }
  | void       { TVoid }

ParamFormais
  : ParamFormais "," ParamFormal { $1 ++ [$3] }
  | ParamFormal                  { [$1] }

ParamFormal
  : Tipo Id                      { $2 :#: ($1, 0) }

BlocoPrincipal
  : "{" Declaracoes ListaCmd "}"  { ($2, $3) }
  | "{" ListaCmd "}"              { ([], $2) }

Declaracoes
  : Declaracoes Declaracao        { $1 ++ $2 }
  | Declaracao                    { $1 }

Declaracao : Tipo ListaId ";"         { map(\id -> id :#: ($1, 0)) $2 }

Tipo 
  : int          { TInt }
  | double       { TDouble }
  | string       { TString }

ListaId
  : ListaId "," Id   { $1 ++ [$3] }
  | Id               { [$1] }

Bloco
  : "{" ListaCmd "}"     { $2 }

ListaCmd
  : ListaCmd Comando    { $1 ++ [$2] }
  | Comando             { [$1] }

Comando
  : CmdSe             { $1 }
  | CmdEnquanto       { $1 }
  | CmdAtrib          { $1 }
  | CmdEscrita        { $1 }
  | CmdLeitura        { $1 }
  | Retorno           { $1 }
  | ChamadaProc       { $1 }

CmdSe 
  : "if" "(" ExpressaoLogica ")" Bloco                 { If $3 $5 [] }
  | "if" "(" ExpressaoLogica ")" Bloco "else" Bloco    { If $3 $5 $7 }

CmdEnquanto
  : "while" "(" ExpressaoLogica ")" Bloco     { While $3 $5 }

CmdAtrib
  : Id "=" ExpressaoAritmetica ";"     { Atrib $1 $3 } 
  | Id "=" LiteralStr ";"              { Atrib $1 (Lit $3) }

CmdEscrita
  : "print" "("  ExpressaoAritmetica ")" ";"  { Imp $3 }
  | "print" "("  LiteralStr ")" ";"           { Imp (Lit $3) }

CmdLeitura
  : "read" "(" Id ")" ";"                      { Leitura $3 }

Retorno
  : "return" ExpressaoAritmetica ";"  { Ret (Just $2) }
  | "return" LiteralStr ";"           { Ret (Just (Lit $2)) }
  | "return" ";"                      { Ret Nothing }

ChamadaProc
  : ChamadaFuncao ";"        { let (Chamada i v) = $1 in Proc i v }

ChamadaFuncao
  : Id "(" ParamReais ")"    { Chamada $1 $3 }
  | Id "(" ")"               { Chamada $1 [] }

ParamReais
  : ParamReais "," ExpressaoAritmetica  { $1 ++ [$3] }
  | ParamReais "," LiteralStr           { $1 ++ [Lit $3] }
  | ExpressaoAritmetica                 { [$1] }
  | LiteralStr                          { [Lit $1] }

ExpressaoAritmetica
  : ExpressaoAritmetica "+" ExpressaoAritmetica  { Add $1 $3 }
  | ExpressaoAritmetica "-" ExpressaoAritmetica  { Sub $1 $3 }
  | ExpressaoAritmetica "*" ExpressaoAritmetica  { Mul $1 $3 }
  | ExpressaoAritmetica "/" ExpressaoAritmetica  { Div $1 $3 }
  | "(" ExpressaoAritmetica ")"                  { $2 }
  | Id                                           { IdVar $1 }
  | Int                                          { Const (CInt $1) }
  | Double                                       { Const (CDouble $1) }                     
  | ChamadaFuncao                                { $1 }

ExpressaoLogica 
  : ExpressaoLogica "&&" ExpressaoLogica  { And $1 $3 }
  | ExpressaoLogica "||" ExpressaoLogica  { Or $1 $3 }
  | "!" ExpressaoLogica                   { Not $2 }
  | ExpressaoRelacional                   { Rel $1 }
  | "(" ExpressaoLogica ")"               { $2 }

ExpressaoRelacional 
  : ExpressaoAritmetica "<" ExpressaoAritmetica  { Rlt $1 $3 }
  | ExpressaoAritmetica ">" ExpressaoAritmetica  { Rgt $1 $3 }
  | ExpressaoAritmetica "<=" ExpressaoAritmetica { Rle $1 $3 }
  | ExpressaoAritmetica ">=" ExpressaoAritmetica { Rge $1 $3 }
  | ExpressaoAritmetica "==" ExpressaoAritmetica { Req $1 $3 }
  | ExpressaoAritmetica "/=" ExpressaoAritmetica { Rdif $1 $3 }

{
parseError :: [Token] -> a
parseError s = error ("Parse error:" ++ show s)

testSin = do
    input <- readFile "../inputs/input.j--"    
    let tokens = L.alexScanTokens (input)
    
    -- Printando AST
    let ast = parser tokens
    putStrLn "\nÁrvore sintática abstrata gerada:\n"
    print ast

{- ÁRVORE SINTÁTICA ABSTRATA FORMATADA COM ESPAÇOS PARA MELHOR VISUALIZAÇÃO

Prog 
[
  "maior" :->: (["a" :#: (TDouble,0),"b" :#: (TDouble,0)],TDouble),
  "fat" :->: (["n" :#: (TInt,0)],TInt),
  "somatorio" :->: (["n" :#: (TInt,0)],TInt),
  "imprimir" :->: (["s" :#: (TString,0),"r" :#: (TDouble,0)],TVoid)
] 



[
  ("maior",
  [
    "a" :#: (TDouble,0),
    "b" :#: (TDouble,0),
    "m" :#: (TInt,0)
  ],
  [
    If (Rel (Rgt (IdVar "a") (IdVar "b"))) 
    [Atrib "m" (IdVar "a")] 
    [Atrib "m" (IdVar "b")],
    Ret (Just (IdVar "m"))
  ]),

  ("fat",
  [
    "n" :#: (TInt,0),
    "f" :#: (TInt,0)
  ],
  [
    Atrib "f" (Const (CInt 0)),
    While (Rel (Rgt (IdVar "n") (Const (CInt 0)))) [
      Atrib "f" (Mul (IdVar "f") (IdVar "n")),
      Atrib "n" (Sub (IdVar "n") (Const (CInt 1)))
    ],
    Ret (Just (IdVar "f"))
  ]),
    
  ("somatorio",
  [
    "n" :#: (TInt,0),
    "i" :#: (TInt,0),
    "s" :#: (TDouble,0)
  ],
  [
    Atrib "s" (Const (CInt 0)),
    Atrib "i" (Const (CInt 0)),
    While (Rel (Rlt (IdVar "i") (IdVar "n"))) [
      Atrib "s" (Add (IdVar "s") (IdVar "i")),
      Atrib "i" (Add (IdVar "i") (Const (CInt 1)))
    ],
    Ret (Just (IdVar "s"))
  ]),

  ("imprimir",
    [
      "s" :#: (TString,0),
      "r" :#: (TDouble,0)
    ],
    [
      Imp (IdVar "s"),
      Imp (IdVar "r"),
      Ret Nothing
    ])
  ]
]



[
  "x" :#: (TInt,0),
  "num" :#: (TInt,0),
  "a" :#: (TDouble,0)
] 
[
  Imp (Lit "Numero:"),
  Leitura "num",
  Atrib "x" (Chamada "fat" [Const (CDouble 4.5)]),
  Atrib "a" (Chamada "maior" [Const (CDouble 2.5),Const (CInt 10)]),
  Proc "imprimir" [Lit "teste:",Const (CInt 1)],
  Ret Nothing
]
-}

}