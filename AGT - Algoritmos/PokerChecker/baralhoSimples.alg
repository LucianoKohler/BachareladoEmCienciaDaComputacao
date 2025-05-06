Algoritmo "baralho simples"

Var
   n1, n2, n3 : caracter
   c1, c2, c3 : inteiro
   t1, t2, t3 : inteiro
   oc1, oc2, oc3 : inteiro

Inicio
   escreval("Digite os naipes e os valores das cartas(De 1 a 13)")
   leia(n1, c1)

   se (c1 <= 13) e (c1 >= 1) então
      se (n1 = "o") ou (n1 = "c") ou (n1 = "e") ou (n1 = "p") então
         t1 <- 0
      senão
         t1 <- 1
      fimse
   senão
      t1 <- 1
   fimse

   //Verificação da carta 1, Naipe na variavel n1, valor da carta na variavel c1
   //Variavel t1 para verificação de carta, recebe 1 se inválida, 0 se válida

   leia(n2, c2)
   se (c2 <= 13) e (c2 >= 1) então
      se (n2 = "o") ou (n2 = "c") ou (n2 = "e") ou (n2 = "p") então
         t2 <- 0
      senão
         t2 <- 1
      fimse
   senão
      t2 <- 1
   fimse

   //Verificação da carta 2, muda apenas o numero das varíaveis para 2

   leia(n3, c3)
   se (c3 <= 13) e (c3 >= 1) então
      se (n3 = "o") ou (n3 = "c") ou (n3 = "e") ou (n3 = "p") então
         t3 <- 0
      senão
         t3 <- 1
      fimse
   senão
      t3 <- 1
   fimse

   //Verificação da carta 3, muda apenas o numero das varíaveis para 3

   //se algum for 1, uma das cartas está fora de padrão, então o código acaba.
   se (t1 = 1) ou (t2 = 1) ou (t3 = 1) então
      escreval("Baralho inválido!")
   senão
      escreval("Baralho válido, checando:")
      //Essa parte do código serve para organizar os valores das cartas...
      //com as variaveis OC1, 2 e 3, caso as cartas são inseridas fora de ordem

      se (c1 < c2) e (c1 < c3) então
         oc1 <- c1
         se c2 < c3 então
            oc2 <- c2
            oc3 <- c3
         senão
            oc2 <- c3
            oc3 <- c2
         fimse
      senão
         se (c2 < c1) e (c2 < c3) então
            oc1 <- c2
            se c1 < c3 então
               oc2 <- c1
               oc3 <- c3
            senão
               oc2 <- c3
               oc3 <- c1
            fimse
         senão
            oc1 <- c3
            se c1 < c2 então
               oc2 <- c1
               oc3 <- c2
            senão
               oc2 <- c3
               oc3 <- c1
            fimse
         fimse
      fimse

      //Para a verificação dos naipes, eles precisam ser iguais

      se (n1 = n2) e (n2 = n3) e (n1 = n3) então
         se (oc2 = oc1 + 1) e (oc3 = oc2 + 1) então
            escreval("Você tem uma sequência")
         senão
            escreval("Você não tem nada")
         fimse
      senão
         escreval("Você não tem nada")
      fimse
   fimse
Fimalgoritmo