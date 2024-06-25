import System.IO
import Data.List (sortBy)
import Data.Ord (comparing)

type Doc = String
type Line = String
type Word' = String
type Index = Int

data Arv a = No a (Arv a) (Arv a) | Nil deriving (Show)

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

insTree e Nil = No e Nil Nil
insTree e (No x esq dir) | e == x = No x esq dir
                         | e < x   = No x (insTree e esq) dir
                         | e > x   = No x esq (insTree e dir)

sortLs [] = Nil
sortLs (x:xs) = insTree x (sortLs xs)

-- E Juntar as ocorrências de cada palavra, para cada palavra, mo	
-- Input teste: almalgamate (sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional")))

amalgamate :: Arv (Index, Word') -> Arv ([Index], Word')
amalgamate Nil = Nil
amalgamate (No (index, word) esq dir) = -- À fazer

-- F Eliminar da lista de números de linhas em que cada palavra ocorre, as repetições de um mesmo número de linha:
-- Input teste: shorten (almalgamate (sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"))))  

-- Função para imprimir árvore:

arvPrint Nil = return []
arvPrint (No x esq dir) = do arvPrint esq
                             putStr (show x ++ "\n")
                             arvPrint dir

-- Input teste: arvPrint(amalgamate (sortLs (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"))))