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

numLinesElegante xs = zip [1..] (lines xs)

-- C apontar em que linha cada palavra aparece
-- Input: allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional")

allNumWords :: [(Int,Line)] -> [(Int,Word')]
allNumWords [] = []
allNumWords ((index, linha):xs) = zip (repeat index) (words linha) ++ allNumWords xs

-- D Ordenar alfabeticamente as ocorrências de palavras no texto
-- Input: sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"))

sortLs :: [(Int,Word')] -> [(Int,Word')]
sortLs = sortBy (comparing snd)

-- E Juntar as ocorrências de cada palavra, para cada palavra, mostrar a lista dos indexes em que a palavra ocorre

-- F Eliminar da lista de números de linhas em que cada palavra ocorre, as repetições de um mesmo número de linha: 

-- "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"
