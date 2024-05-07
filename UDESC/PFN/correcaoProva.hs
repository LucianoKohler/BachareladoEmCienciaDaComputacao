foo a b = if a == b then b else if a > b then foo (a-b) b else foo a (b-a)

impares :: [Int] -> [Int]
impares [] = [] -- Coloquei % ao invés de mod
impares (x:xs) | mod x 2 == 1 = x:impares xs
               | otherwise = impares xs

-- Não coloquei o Eq na declaração de tipos
remover :: Eq a => a -> [a] -> [a]
remover _ [] = []
remover y (x:xs) | y == x = remover y xs
                 | otherwise = x:remover y xs

todos :: [Bool] -> Bool
todos [] = True
todos (x:xs) | x == False = False
             | otherwise = todos xs

segundos :: [(a,b)] -> [b]
segundos [] = []
segundos (x:xs) = snd x : segundos xs