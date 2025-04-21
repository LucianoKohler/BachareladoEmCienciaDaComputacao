# Exercício 1:

Crie um programa em Java que leia do usuário as informações relacionadas a várias pessoas.
Cada pessoa possui nome, idade, cpf e cidade. O programa termina a leitura quando o usuário digita o valor **-1**. A cada nova pessoa lida, o programa deve exibir a lista de pessoas (ordenada alfabeticamente pelo nome) com suas respectivas informações, agrupando-as de acordo com as faixas etárias a seguir:

- 01 a 12 anos:  **Crianças**;
- 13 a 18 anos: **Adolescentes**; 
- 19 a 25 anos: **Jovens**; 
- 25 a 59 anos: **Adultos**;
- 60 para cima: **Idosos**;

# Exercício 2:
Crie a classe Matriz&lt;T>, ela deve possuir como atributos a ordem da matriz e uma matriz de elementos do tipo T a serem alocados pelo método construtor, que deve receber dois parâmetros **n** e **m**, estes correspondentes a ordem da matriz. Crie um enumerável Quadrante, contendo os valores: PRIMEIRO, SEGUNDO, TERCEIRO e QUARTO. A classe Matriz, deve possuir os seguintes métodos:
- ```boolean set(T objeto, int i, int j)```
- ```T get(int i, int j)```
- ```List<T> getLinha(int linha)```
- ```List<T> getColuna(int coluna)```
- ```List<T> getElementosQuadrante(Quadrante quadrante)```

# Exercício 3:
Utilizando a classe criada no exemplo anterior, crie um programa em Java que leia do console uma matriz 5 × 5, até que o usuário digite -1 e exiba no console o **menor elemento presente na matriz**. Para encontrar o menor elemento, o programa deve encontrar o menor elemento dos quadrantes e exibir o menor deles.

***NOTA DO ALUNO:*** Como o professor pede para encontrar o menor valor da matriz no exercício 3, entende-se que a matriz conterá valores numéricos, então ao invés de utilizar tipos genéricos **T**, utilizei **Integers** no lugar.