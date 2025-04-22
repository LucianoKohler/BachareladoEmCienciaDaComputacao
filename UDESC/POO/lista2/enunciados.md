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

# Exercício 4:
Crie um programa em Java que possui as classes Equipe, Aluno e Turma. A Turma possui uma lista de Alunos, estes com nome, idade e cinco notas. A classe Turma possui um método para adicionar novos alunos e um método privado ```ordenarAlunosPorMedia()```, esse método deve rearranjar a lista em ordem crescente de média. Além disso, a classe Turma deve possuir o método ```separarEmEquipes()```, que retorna uma lista de equipes contendo 3 ou 4 alunos.

*Nota: Estes agrupados levando em consideração a média. Agrupe os alunos pegando um ou dois elementos do começo da lista ordenada por média e um ou dois alunos do final da lista.*

### Após isso, faça com que o programa:
1. Leia os alunos
2. Adicione eles na turma
3. Separe-os com o método ```separarEmEquipes()```
4. Exibir as equipes, seus membros e a média de cada um

# Exercício 5
Crie um algoritmo em Python que seja capaz de ler um ```.json``` e transformá-lo em um ou vários objetos do tipo Filme. Cada Filme possui um **titulo**, **ano**, **classificação indicativa** e **nota** dos usuários. O programa deve ser capaz de listar todos os filmes do arquivo ```.json``` e ordená-los por nota. Na classe Filme deve haver um método que dado um pedaço de ```.json``` recebido como parâmetro, que represente um Filme, atribua os valores do ```.json``` aos atributos do objeto. O algoritmo deve ser capaz de receber os dados na forma descrita a seguir:

```JSON
[
{
  "titulo": "A Rainy Day in New York",
  "ano": 2019,
  "classificacao": "PG-13",
  "nota": 6.6
},
{
  "titulo": "Jurassic World: Fallen Kingdom",
  "ano": 2018,
  "classificacao": "PG-13",
  "nota": 6.2
},
...
]
```

*Nota: Veja como utilizar a biblioteca JSON para converter uma string em um dicionário*

Os dados foram retirados do dataset Complete [IMDB Movies Dataset](https://www.kaggle.com/datasets/gorochu/complete-imdb-movies-dataset) e adaptadas as tags para o português de forma a tornar o exemplo mais didático. Caso deseje, utilize o arquivo.json original e transforme-o para um objeto
