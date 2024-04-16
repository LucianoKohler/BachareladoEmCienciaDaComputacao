-- 1. Declare uma funcao que verifica se um elemento pertence a uma lista

pertence :: Int -> [Int] -> Bool
pertence _ [] = False
pertence n (xs:ys) = if n == xs then True else pertence n ys

-- 2. Declare uma funcao que retorne a intercessãa entre duas listas

intercessao :: [Int] -> [Int] -> [Int]
intercessao [] _ = []
intercessao (x:xs) ys
   | pertence x ys = x : intercessao xs ys
   | otherwise = intercessao xs ys

-- 3. Declare uma funcao que retorne o inverso de uma lista

inverso :: [Int] -> [Int]
inverso [] = []
inverso (x:xs) = inverso xs ++ [x]

-- 4. Declare uma funcao que retorne os n ultimos elementos de uma lista

remover :: Int -> [Int] -> [Int]
remover 0 xs = xs
remover n (x:xs) = remover (n-1) xs

nUltimos :: Int -> [Int] -> [Int]
nUltimos _ [] = []
nUltimos n xs = remover (length xs -n) xs

-- 5. Declare uma funcao que receba duas listas de numeros e crie uma lista com a soma das listas

soma2 :: [Int] -> [Int] -> [Int]
soma2 _ [] = []
soma2 [] _ = []
soma2 (x:xs) (y:ys) = x+y:soma2 xs ys

-- 6. Declare uma funcao que receba como parametro um numero N e retorne uma lista com todas as potencias de 2 ate 2eN

pot2 :: Int -> [Int]
pot2 1 = [2]
pot2 n = pot2 (n-1) ++ [2^n]

-- 7. Receba duas listas, concatene e ordene

-- 8. Declare uma funcao que retorne o menor valor de uma lista

menor :: [Int] -> Int
menor [] = 0
menor [x] = x
menor (x:y:xs) 
   |x > y = menor (y:xs)
   |otherwise = menor (x:xs)


-- 9. Declare uma funcao que receba uma lista e um elemento e retorne a lista sem a primeira ocorrencia desse elemento

-- 10. Ordene uma lista



main :: IO()
main = do
   putStrLn "Rodando."
