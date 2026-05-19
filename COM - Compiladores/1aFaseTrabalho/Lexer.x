{
module Lexer where

import Token
}

%wrapper "basic"

-- Regex simples
$digit = [0-9]
$alpha = [a-zA-z]

-- Regex composto
@int = '-'? $digit+
@double = '-'? $digit+ \. $digit* | \. $digit+
@string = [$alpha$digit]*
@literal = \" ___ \" -- Ver o que incluir aqui 

tokens :-

-- ============== COMENTÁRIOS E ESPAÇOS ===============



{
testLex = do 
  putStr "Output do léxico:\n"
  s <- readFile "teste.j--_"
  print (alexScanTokens s)
}