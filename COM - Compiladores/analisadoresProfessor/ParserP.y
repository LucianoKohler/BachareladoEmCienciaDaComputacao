{
module Parser where

import Token
import qualified Lex as L

}

-- Gramatica ambigua, ao usar o happy, vai dar erro. 
-- Usando a flag -info, é possível ver o porquê

%name calc
%tokentype { Token }
%error { parseError }
%token 
  '+' {ADD}
  '-' {SUB}
  '*' {MUL}
  '/' {DIV}
  '(' {LPAR}
  ')' {RPAR}
  Num {NUM $$}

%left '+' '-'
%left '*' '/'

%%

Expr  : Expr '+' Expr    {$1 + $3}
      | Expr '-' Expr    {$1 - $3}
      | Expr '*' Expr    {$1 * $3}
      | Expr '/' Expr    {$1 / $3}
      | Num              {$1}
      | '(' Expr ')'     {$2}      


{
parseError :: [Token] -> a
parseError s = error ("Parse error:" ++ show s)

main = do putStr "Expressão:"
          s <- getLine
          print (calc (L.alexScanTokens s))
}