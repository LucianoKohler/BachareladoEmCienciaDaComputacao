import Data.List (sortBy)
import Data.Ord (comparing)

type Doc = String

type Line = String

type Word' = String

-- A já está resolvida com o comando Lines

-- lines :: Doc -> [Line]
-- lines x = lines x

-- B Numerar as linhas com um index
-- Input: numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"

numLines :: Doc -> [(Int, Line)]
numLines str = numLinesAux 1 (lines str)

numLinesAux :: Int -> [Line] -> [(Int, Line)]
numLinesAux _ [] = []
numLinesAux num (linha : linhas) = (num, linha) : numLinesAux (num + 1) linhas

numLinesElegante xs = zip [1 ..] (lines xs)

-- C apontar em que linha cada palavra aparece
-- Input: allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional")

allNumWords :: [(Int, Line)] -> [(Int, Word')]
allNumWords [] = []
allNumWords ((index, linha) : xs) = zip (repeat index) (words linha) ++ allNumWords xs

-- D Ordenar alfabeticamente as ocorrências de palavras no texto
-- Input: sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"))

sortLs :: [(Int, Word')] -> [(Int, Word')]
sortLs = sortBy (comparing snd)

-- E Juntar as ocorrências de cada palavra, para cada palavra, mostrar a lista dos indexes em que a palavra ocorre
-- Input exemplo: [(1, "teste"), (2, "Teste"), (2, "carro")] -> [([1, 2], "teste"), ([2], "carro")]
-- Input: almalgamate (sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional")))

toLowercase :: String -> String
toLowercase [] = []
toLowercase (c : xs)
  | c >= 'A' && c <= 'Z' = toEnum (fromEnum c + 32) : toLowercase xs
  | otherwise = c : toLowercase xs

almalgamate :: [(Int, Word')] -> [([Int], Word')]
almalgamate (x : xs) = almalgamateAux xs [fst x] (snd x)

almalgamateAux [] index palavra = [(index, toLowercase palavra)]
almalgamateAux (x : xs) index palavra
  | toLowercase palavra == toLowercase (snd x) = almalgamateAux xs (fst x : index) palavra
  | otherwise = (index, toLowercase palavra) : almalgamateAux xs [fst x] (snd x)

-- F Eliminar da lista de números de linhas em que cada palavra ocorre, as repetições de um mesmo número de linha:
-- Input:  shorten (almalgamate (sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"))))

pertence y [] = False
pertence y (x : xs) = if y == x then True else pertence y xs

removeRep [] = []
removeRep (x : xs) = if pertence x xs then removeRep xs else x : removeRep xs

shorten :: [([Int], Word')] -> [([Int], Word')]
shorten [] = []
shorten (x : xs) = ((removeRep (fst x)), snd x) : shorten xs
  
-- "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"
