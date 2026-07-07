module Token where

data Token 
    
    -- Tipos
    = TINT | TDOUBLE | TSTRING | TVOID
    
    -- Valores
    | ID String | CDOUBLE Double | CINT Int | LITERAL String 
    
    -- Relacionais
    | LESS | GRTR | LEQ | GEQ | EQUAL | NEQUAL -- <, >, <=, >=, = e /   =
    
    -- Lógi os
    | AND | OR | NOT

    -- Aritméticos
    | ADD | SUB | MUL | DIV -- +, -, * e /
    | LPAR | RPAR | LBRACE | RBRACE -- () e {}
    | COMMA | SEMICOLON -- , e ;
    
    -- Comandos
    | IF | ELSE | WHILE | FOR | ATTRIB | READ | PRINT | RETURN

    deriving(Eq, Show)

{-
NOTAS:
    - ATTRIB é "="
    - LITERAL é só "abc" por exemplo pro print
-}