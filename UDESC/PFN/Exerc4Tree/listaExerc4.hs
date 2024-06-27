import System.IO
import Data.List (sortBy)
import Data.Ord (comparing)

type Doc = String
type Line = String
type Word' = String
type Index = Int

data Arv = Node Word' [Index] Arv Arv | Nil deriving (Show)

-- A já está resolvida com o comando Lines

-- lines :: Doc -> [Line]
-- lines x = lines x

-- B Numerar as linhas com um index

numLines xs = zip [1 ..] (lines xs)

-- C apontar em que linha cada palavra aparece

allNumWords :: [(Index, Line)] -> [(Index, Word')]
allNumWords [] = []
allNumWords ((index, linha) : xs) = zip (repeat index) (words linha) ++ allNumWords xs

-- D Ordenar alfabeticamente as ocorrências de palavras no texto
-- E Juntar as ocorrências de cada palavra, para cada palavra

listToTree [] =  Nil
listToTree ((ind, pal):xs) = insTree pal ind (listToTree xs)

insTree _ _ [] = Nil Nil
insTree p i (Node pal li esq dir) | p == pal = Node p (insOrd i li) esq dir
                                  | p < pal  = Node p (insTree e esq) dir
                                  | p > pal  = Node p esq (insTree e dir)

toLowercase :: String -> String
toLowercase [] = []
toLowercase (c : xs)
  | c >= 'A' && c <= 'Z' = toEnum (fromEnum c + 32) : toLowercase xs
  | otherwise = c : toLowercase xs

arvPrint Nil = return []
arvPrint (Node pal li esq dir) = do arvPrint esq
                                    putStr (show pal ++ ", " ++ show li ++ "\n")
                                    arvPrint dir



main = do putStr ("Insira o caminho relativo do arquivo: ")
          hFlush stdout  -- Força o output a aparecer primeiro
          file <- getLine
          content <- readFile file
  
          putStr ("\nArquivo lido:")
          putStr (show file) 
  
  -- RESPOSTA
          putStr("\n\nDocumento indexado: ")
          arvPrint(listToTree(allNumWords (numLines content)))

-- Input teste: arvPrint(almalgamate (listToTree (allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional"))))
