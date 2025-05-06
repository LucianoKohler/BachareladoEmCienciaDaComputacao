
inverterDupla :: [(Int, Int)] -> [(Int, Int)]
inverterDupla [] = []
inverterDupla (x:xs) = (snd x, fst x) : inverterDupla xs

zip' :: [Int] -> [String] -> [(Int, String)]
zip' [] xs = []
zip' xs [] = []
zip' (x:xs) (y:ys) = (x, y) : zip xs ys

semVogal :: [String] -> [String]
semVogal [] = []
semVogal (x:xs) = removeVogal x : semVogal xs

removeVogal :: String -> String
removeVogal [] = []
removeVogal (x:xs) = if x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u' then removeVogal xs else x:removeVogal xs  

main = putStrLn "Hello World"