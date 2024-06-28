import System.IO

-- Para legibilidade na tipagem das funções
type Doc = String
type Line = String
type Word' = String
type Index = Int

-- Estrutura da árvore
data Arv = Node Word' [Index] Arv Arv | Nil deriving (Show)

-- A já está resolvida com o comando Lines

-- lines :: Doc -> [Line]
-- lines x = lines x

-- B Numerar as linhas com um index

numLines :: Doc -> [(Index, Line)]
numLines xs = zip [1 ..] (lines xs)

-- C apontar em que linha cada palavra aparece
-- Input teste: allNumWords (numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional")

allNumWords :: [(Index, Line)] -> [(Index, Word')]
allNumWords [] = []
allNumWords ((index, linha) : xs) = zip (repeat index) (words linha) ++ allNumWords xs

-- D Ordenar alfabeticamente as ocorrências de palavras no texto
-- E Juntar as ocorrências de cada palavra, para cada palavra
-- F Remover as ocorrências repetidas de cada palavra

-- Funções auxiliares
toLowercase :: String -> String
toLowercase [] = []
toLowercase (c : xs)
  | c >= 'A' && c <= 'Z' = toEnum (fromEnum c + 32) : toLowercase xs
  | otherwise = c : toLowercase xs
  
insOrd :: Int -> [Int] -> [Int]
insOrd x [] = [x]
insOrd x (y:ys)
  | x <= y = x:y:ys
  | otherwise = y : insOrd x ys
-- Fim funções auxiliares

insTree :: Word' -> Index -> Arv -> Arv
insTree pal ind Nil = Node pal [ind] Nil Nil
insTree pal ind (Node pal' indList esq dir) 
  | pal == pal' = Node pal' (insOrd ind indList) esq dir
  | pal < pal'  = Node pal' indList (insTree pal ind esq) dir
  | pal > pal'  = Node pal' indList esq (insTree pal ind dir)

mIndexTree :: [(Index, Word')] -> Arv
mIndexTree [] =  Nil
mIndexTree ((ind, pal):xs) = insTree (toLowercase pal) ind (mIndexTree xs)

-- Imprimir a ávrore

arvPrint Nil = return []
arvPrint (Node pal li esq dir) = do arvPrint esq
                                    putStr (show pal ++ ", " ++ show li ++ "\n")
                                    arvPrint dir

main = do   putStr ("Insira o caminho relativo do arquivo: ")
            hFlush stdout  -- Força o output a aparecer primeiro
            file <- getLine
            content <- readFile file
            
            putStr ("\nArquivo lido:")
            putStr (show file) 
            
            -- RESPOSTA
            putStr("\n\nDocumento indexado: ")
            arvPrint(mIndexTree(allNumWords(numLines "Departamento de Ciencia da Computacao\nCurso de Ciencia da Computacao\nProgramacao Funcional")))
          
