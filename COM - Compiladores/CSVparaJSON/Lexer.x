{
module Lexer where

import Token
}

%wrapper "basic"

-- Errado:  joao -> TEXT "j" TEXT "o" TEXT "a" TEXT "o"
-- Correto: joao -> TEXT "joao"

@textoAspas = [\ ]*\"[^\"\r]*\"
@textoSemAspas = [^\,\n\r\"]+

tokens :-

<0> [ \r\t]+         ;
<0> \,               {\s -> VIRG}
<0> \n[\ ]*          {\s -> NEWL}
<0> @textoAspas      {\s -> TEXT (tail (init (dropWhile (==' ') s)))} -- Remove espaços antes e não retorna as aspas
<0> @textoSemAspas   {\s -> TEXT (dropWhile (==' ') s)} -- Remove espaços antes

{
testLex = do 
  putStr "Output do léxico:\n"
  s <- readFile "registro.csv"
  print (alexScanTokens s)
}