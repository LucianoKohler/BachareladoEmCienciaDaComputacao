par x = rem x 2 == 0

filter par [1, 2, 5, 7, 4] -- [2, 4]
filter (\x -> not(par x)) [1, 2, 5, 7, 4] -- [1, 5, 7]
-- ou 
filter not.par [1, 2, 5, 7, 4] -- [1, 5, 7] (composição)