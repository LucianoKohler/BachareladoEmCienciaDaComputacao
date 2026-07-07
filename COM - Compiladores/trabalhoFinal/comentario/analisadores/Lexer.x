{
module Lexer where

import Token
}

%wrapper "basic"

-- Regex simples
$digit = 0-9
$alpha = [a-zA-z]

-- Regex composto
@id = $alpha [$alpha $digit \_]*
@int = '-'? $digit+
@double = '-'? $digit+ \. $digit* | \. $digit+
@literal = \" [^\"]* \"

tokens :-

-- ============== COMENTÁRIOS E ESPAÇOS ===============

<0> $white+                               ;
<0> "//" .*                               ;

-- ================= TIPOS DE DADOS ===================

<0> "int"            { \s -> TINT }
<0> "double"         { \s -> TDOUBLE }
<0> "string"         { \s -> TSTRING }
<0> "void"           { \s -> TVOID }

-- ============= OPERADORES RELACIONAIS ===============

<0> "<"             { \s -> LESS }
<0> "<="            { \s -> LEQ }
<0> ">"             { \s -> GRTR }
<0> ">="            { \s -> GEQ }
<0> "=="            { \s -> EQUAL }
<0> "/="            { \s -> NEQUAL }

-- =============== OPERADORES LÓGICOS ================

<0> "&&"            { \s -> AND }
<0> "||"            { \s -> OR }
<0> "!"             { \s -> NOT }

-- ============= OPERADORES ARITMÉTICOS ==============

<0> "+"             { \s -> ADD }
<0> "-"             { \s -> SUB }
<0> "*"             { \s -> MUL }
<0> "/"             { \s -> DIV }

-- =============== ESCOPOS E VÍRGULAS ================

<0> "{"             { \s -> LBRACE }
<0> "}"             { \s -> RBRACE }
<0> "("             { \s -> LPAR }
<0> ")"             { \s -> RPAR }
<0> ","             { \s -> COMMA }
<0> ";"             { \s -> SEMICOLON }

-- ==================== COMANDOS =====================

<0> "if"            { \s -> IF }
<0> "else"          { \s -> ELSE }
<0> "while"         { \s -> WHILE }
<0> "="             { \s -> ATTRIB }
<0> "read"          { \s -> READ }
<0> "print"         { \s -> PRINT }
<0> "return"        { \s -> RETURN }

-- ==================== VALORES =====================

<0> @id            { \s -> ID s }
<0> @int           { \s -> CINT (read s) }
<0> @double        { \s -> CDOUBLE (read s) }
<0> @literal       { \s -> LITERAL (tail(init(s))) } -- Remove "" da literal

{
testLex = do 
  putStr "Output do léxico:\n"
  s <- readFile "../inputs/input.j--"
  print (alexScanTokens s)

-- OUTPUT FORMATADO PARA VISUALIZAÇÃO
  
{-

[
  TDOUBLE,ID "maior",
  LPAR,TDOUBLE,ID "a",COMMA,TDOUBLE,ID "b" RPAR,
  LBRACE,
    TINT,ID "m",SEMICOLON,
    IF,LPAR,ID "a",GRTR,ID "b",RPAR,
    LBRACE,
      ID "m",ATTRIB,ID "a",SEMICOLON,
    RBRACE,
    ELSE,
    LBRACE,
      ID "m",ATTRIB,ID "b",SEMICOLON,
    RBRACE,
    RETURN,ID "m",SEMICOLON,
  RBRACE,

  TINT,ID "fat",
  LPAR,TINT,ID "n",RPAR,
  LBRACE,
    TINT,ID "f",SEMICOLON,
    ID "f",ATTRIB,CINT 0,SEMICOLON,
    WHILE,LPAR,ID "n",GRTR,CINT 0,RPAR,
    LBRACE,
      ID "f",ATTRIB,ID "f",MUL,ID "n",SEMICOLON,
      ID "n",ATTRIB,ID "n",SUB,CINT 1,SEMICOLON,
    RBRACE,
    RETURN,ID "f",SEMICOLON,
  RBRACE,

  TINT,ID "somatorio",
  LPAR,TINT,ID "n",RPAR,
  LBRACE,
    TINT,ID "i",SEMICOLON,
    TDOUBLE,ID "s",SEMICOLON,
    ID "s",ATTRIB,CINT 0,SEMICOLON,
    ID "i",ATTRIB,CINT 0,SEMICOLON,
    WHILE,LPAR,ID "i",LESS,ID "n",RPAR,
    LBRACE,
      ID "s",ATTRIB,ID "s",ADD,ID "i",SEMICOLON,
      ID "i",ATTRIB,ID "i",ADD,CINT 1,SEMICOLON,
    RBRACE,
    RETURN,ID "s",SEMICOLON,
  RBRACE,

  TVOID,ID "imprimir",LPAR,TSTRING,ID "s",COMMA,TDOUBLE,ID "r",RPAR,
  LBRACE,
    PRINT,LPAR,ID "s",RPAR,SEMICOLON,
    PRINT,LPAR,ID "r",RPAR,SEMICOLON,
    RETURN,SEMICOLON,
  RBRACE,
  
  LBRACE,
    TINT,ID "x",COMMA,ID "num",SEMICOLON,
    TDOUBLE,ID "a",SEMICOLON,
    PRINT,LPAR,LITERAL "Numero:",RPAR,SEMICOLON,
    READ,LPAR,ID "num",RPAR,SEMICOLON,
    ID "x",ATTRIB,ID "fat",LPAR,CDOUBLE 4.5,RPAR,SEMICOLON,
    ID "a",ATTRIB,ID "maior",LPAR,CDOUBLE 2.5,COMMA,CINT 10,RPAR,SEMICOLON,
    ID "imprimir",LPAR,LITERAL "teste:",COMMA,CINT 1,RPAR,SEMICOLON,
    RETURN,SEMICOLON,
  RBRACE
]

-}

}
