Algoritmo "É PRIMO"

Var
   i : Inteiro
   n : Inteiro
   ePrimo : Logico
Inicio
      Escreva("Insira um número:")
      leia(n)
      ePrimo := verdadeiro
      
      Para i de 2 Até n-1 Faça
           Se (n mod i = 0) Entao
              ePrimo := falso
           FimSe
      fimPara

      Se ePrimo = verdadeiro entao
          Escreval(n, " é primo!")
      Senao
         Escreval(n, " não é primo")
      FimSe
Fimalgoritmo
            V = 12
            T = 2
            t = 0.1
            (V * sen(T)) * t - 1/2 * g * g^2