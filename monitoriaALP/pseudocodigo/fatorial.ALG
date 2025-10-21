Algoritmo "Fatorial"

Var
   i : Inteiro
   n : Inteiro
   fat : Inteiro

Inicio
      Escreva("Digite um  n�mero:")
      leia(n)
      fat := 1
      Para i de 1 At� n Fa�a
           fat := fat * i
      fimPara
      
      Escreval("Fatorial de", n, ":", fat)
Fimalgoritmo