{-
Declare uma função que receba as 3 medidas dos lados de um triangulo, a função deve 
informar se as medidas podem formar um triângulo Retornando a True em caso 
afirmativo e False caso contrário: 
-}

ehTriangulo :: Float -> Float -> Float -> Bool
ehTriangulo x y z =  if x < y + z && y < x + z && z < x + y then True else False

{-
Declare uma função que receba 3 medidas válidas dos lados de um triângulo e retorne
se esse triângulo é equilátero, isósceles ou escaleno. O retorno deve ser uma String
contendo a classificação do triângulo
-}

tipoTriangulo :: Float -> Float -> Float -> String
tipoTriangulo x y z = 
    if x == y && y == z then "equilatero" else 
    if x == y || y == z || x == z then "isosceles" 
    else "escaleno"

{-
Usando as funções declaradas nos exercícios anteriores defina uma função que receba
as 3 medidas dos lados de um triângulo e retorne se essas medidas formam um triângulo,
em caso afirmativo a função deve retornar o tipo do triângulo: equilátero, isósceles ou
escaleno, caso contrário deve retornar a string: “não eh um triangulo”.
-}

triangulo :: Float -> Float -> Float -> String
triangulo x y z = 
    if ehTriangulo x y z then
        tipoTriangulo x y z
    else "nao eh um triangulo"

{-
Declare uma função que receba como parâmetro um inteiro n e retorne a soma dos
números pares entre 0 e n
-}

somaPares :: Integer -> Integer
somaPares 0 = 0
somaPares n = 
   if even n  
   then n + somaPares (n-2)
   else somaPares (n-1)

{-
Declare uma função que receba inteiros (m e n) e retorne a seguinte série: 
2^0m + 2^1m + 2^2m + ... + 2^nm
-}

somaPot2m :: Integer -> Integer -> Integer
somaPot2m m 0 = m
somaPot2m m n = (2^n)*m + somaPot2m m (n-1)

{-
Declare uma função que receba um número e retorne True caso o número seja primo e
False caso contrário. Um número primo é um número natural maior que 1, e que possui
apenas dois divisores: 1 e ele mesmo
-}

primo :: Integer -> Bool
primo n = primoAux n (n-1)

-- Pra passar 2 valores ao invés de só n para a recursao

primoAux :: Integer -> Integer -> Bool
primoAux n 1 = True
primoAux n m =
  if mod n m == 0
    then False
    else primoAux n (m-1)

{-
Uma aproximação para o valor de π pode ser obtida por meio da série:

4/1 – 4/3 + 4/5 – 4/7 + 4/9 – 4/11 + ...

Declare uma função chamada seriePI que receba como parâmetro um inteiro n e
calcule o valor da série enquanto o termo for maior que 4/n. Execute os seguintes
testes
-}

{- À fazer -}
main :: IO()
main = do
    putStrLn "RODANDO."