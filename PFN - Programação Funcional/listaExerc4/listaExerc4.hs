import System.IO
import Data.List (sortBy)
import Data.Ord (comparing)

type Doc = String
type Line = String
type Word' = String
type Index = Int

-- A já está resolvida com o comando Lines

-- lines :: Doc -> [Line]
-- lines x = lines x

-- B Numerar as linhas com um index
-- Input teste: numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"

numLines :: Doc -> [(Index, Line)]
numLines str = numLinesAux 1 (lines str)

numLinesAux :: Index -> [Line] -> [(Index, Line)]
numLinesAux _ [] = []
numLinesAux num (linha : linhas) = (num, linha) : numLinesAux (num + 1) linhas

numLinesElegante xs = zip [1 ..] (lines xs)

-- C apontar em que linha cada palavra aparece
-- Input teste: allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional")

allNumWords :: [(Index, Line)] -> [(Index, Word')]
allNumWords [] = []
allNumWords ((index, linha) : xs) = zip (repeat index) (words linha) ++ allNumWords xs

-- D Ordenar alfabeticamente as ocorrências de palavras no texto
-- Input teste: sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"))

sortLs :: [(Index, Word')] -> [(Index, Word')]
sortLs = sortBy (comparing snd)

-- E Juntar as ocorrências de cada palavra, para cada palavra, mo	
-- Input teste: almalgamate (sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional")))

toLowercase :: String -> String
toLowercase [] = []
toLowercase (c : xs)
  | c >= 'A' && c <= 'Z' = toEnum (fromEnum c + 32) : toLowercase xs
  | otherwise = c : toLowercase xs

almalgamate :: [(Index, Word')] -> [([Index], Word')]
almalgamate (x : xs) = almalgamateAux xs [fst x] (snd x)

almalgamateAux [] index palavra = [(index, toLowercase palavra)]
almalgamateAux (x : xs) index palavra
  | toLowercase palavra == toLowercase (snd x) = almalgamateAux xs (fst x : index) palavra
  | otherwise = (index, toLowercase palavra) : almalgamateAux xs [fst x] (snd x)

-- F Eliminar da lista de números de linhas em que cada palavra ocorre, as repetições de um mesmo número de linha:
-- Input teste: shorten (almalgamate (sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"))))

pertence y [] = False
pertence y (x : xs) = if y == x then True else pertence y xs

removeRep [] = []
removeRep (x : xs) = if pertence x xs then removeRep xs else x : removeRep xs

shorten :: [([Index], Word')] -> [([Index], Word')]
shorten [] = []
shorten (x : xs) = ((removeRep (fst x)), snd x) : shorten xs
  
main = do
  
  putStr ("Insira o caminho relativo do arquivo: ")
  hFlush stdout  -- Força o output a aparecer primeiro
  file <- getLine
  content <- readFile file
  
  putStr ("\nArquivo lido:")
  putStr (show file) 
  
  -- RESPOSTA
  let resposta = shorten (almalgamate (sortLs (allNumWords (numLines content))))
  putStr("\n\nDocumento indexado: ")
  putStr(show resposta)
 
  
