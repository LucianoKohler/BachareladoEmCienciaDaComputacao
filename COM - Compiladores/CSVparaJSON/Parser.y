-- Para compilar, rodar alex Lexer.x, happy --ghc Parser.y e por fim, ghci Parser.hs
-- Só chamar a main no GHCI daí :D

{
module Parser where

import Token
import qualified Lexer as L
import Data.List (intercalate) -- Pro JSON
}

%name parseCSV
%tokentype { Token }
%error { parseError }
%token 
  ','  {VIRG}
  '\n' {NEWL}
  Str  {TEXT $$}

-- TEXT "Nome" VIRG TEXT "Cidade" NEWL ...
-- CSV ["Nome","Cidade"] [["Joao","SP"],["Maria","RJ"]]

%%

CSV : Cabecalho '\n' Registros {CSV $1 $3}

-- Primeiro registro
Cabecalho
  : Registro { $1 }

-- Resto dos registros
Registros
  : Registro                  { [$1] }
  | Registro '\n' Registros   { $1 : $3 }
  |                           { [] }
  
-- Um registro, tecnicamente uma "linha"
Registro
  : Valor                     { [$1] }
  | Valor ',' Registro        { $1 : $3 }

-- Elemento dentro de um registro
Valor
  : Str { $1 };

{
parseError :: [Token] -> a
parseError s = error ("Parse error:" ++ show s)

main = do
    input <- readFile "registro.csv"    
    let arv = parseCSV (L.alexScanTokens (input))
    
    -- Printando AST
    putStrLn "\nÁrvore sintática abstrata gerada:\n"
    print arv
    
    -- Gerando JSON
    let output = csvToJson arv
    writeFile "saida.json" output
    
    putStrLn "\nJSON gerado no arquivo 'saida.json'\n"

-- FUNÇÕES PRA GERAR O JSON --

-- Input vira string principal (adicionando o [] de abertura e fechamento do JSOM)
csvToJson :: CSV -> String
csvToJson (CSV cab regs) = 
    "[\n" ++ intercalate ",\n" (map (registroToJson cab) regs) ++ "\n]"
  
-- intercalate coloca ",\n" no meio de cada elemento que (map (registroToJSON cab) regs) retorna

-- map aplica a função (registroToJSON cab) pra cada reg, ou seja, chama a função pra cada reg,
-- mas o primeiro parâmetro é sempre o mesmo (cab)

--------------

-- registroToJSON ["Nome", "Estado"] ["Cristiano", "SP"] -> [{Nome: "Cristiano"}, {Estado: "SP"}]
registroToJson :: Cabecalho -> Registro -> String
registroToJson cab reg = 
    "  {\n" ++ intercalate ",\n" (zipWith itemToJson cab reg) ++ "\n  }"

-- Intercalate coloca , entre cada elemento exceto no final e no inicio
-- zipWith combina cab e reg por meio da função itemToJson

--------------

-- itemToJson nome Cristiano -> 'Nome: "Cristiano"'
itemToJson :: String -> String -> String
itemToJson campo valor = "    \"" ++ campo ++ "\": \"" ++ valor ++ "\""
}