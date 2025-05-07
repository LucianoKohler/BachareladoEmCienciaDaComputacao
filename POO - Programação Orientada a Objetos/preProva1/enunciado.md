# Questão 1
Implemente o diagrama de classes abaixo que representa parcialmente um sistema gerenciamento de biblioteca de uma universidade. O modelo apresenta três camadas, sendo elas: **dados, negócio e apresentação**.
* O sistema realiza empréstimo de livros e monografias, sendo que os livros têm 15 dias de “tempoEmprestimo” enquanto as monografias têm 30 dias. Cada empréstimo pode conter um livro ou uma monografia e são realizados por um usuário cadastrado no sistema.
* Os itens emprestados para um usuário não podem ser emprestados para outros usuários enquanto não for devolvido.
* Um usuário só pode emprestar um livro ou uma monografia caso o limite de livros ou monografias emprestados não seja excedido.
* Quando o empréstimo é devolvido o seu status “devolvido” é setado como “true” e o status do Item “emprestado” é setado como “false”.
* A classe Principal deve possibilitar o cadastro de usuários e itens, assim como o empréstimo e renovação de empréstimo de itens para usuários já cadastrados.
* A classe Principal deve mostrar os usuários e os itens que eles emprestaram, assim como os itens emprestados e os usuários que os emprestaram, um em cada método.
* A classe principal deve ter um método que verifica todos os empréstimos em atraso. Caso um empréstimo esteja atrasado, o tempo de penalidade sem empréstimo para o usuário deve ser setado acrescido em 1 dia para cada dia de atraso para cada item em atraso.
* A Classe Item é abstrata pois o método emprestar() é abstrato e precisa ser implementado pelas suas subclasses atribuindo a data certa de devolução a partida quantidade de dias de empréstimo do livro ou monografia.


# Questão 2
 Implemente em Java um programa para manipular formas geométricas. No pacote de dados, crie a seguinte hierarquia de classes:
* Uma classe abstrata para padronizar o comportamento de qualquer FormaGeométrica, que que tem como atributos a posição x e y da forma no plano cartesiano, e define os métodos abstratos para cálculo do perímetro e cálculo da área;
* Uma classe abstrata para representar Quadrilátero que é uma subclasse de FormaGeométrica. Seu construtor deve receber a posição x, y e o tamanho dos 4 lados, e implementar o método para cálculo do perímetro;
* Uma classe para representar TriânguloEquilátero que é uma subclasse de FormaGeométrica. Seu construtor deve receber a posição x, y e o tamanho do seu lado. Os métodos para cálculo do perímetro e da área devem ser implementados;
* Classes para representar Retângulo e Quadrado que são subclasses de Quadrilátero. A primeira deve receber a posição x, y, e o tamanho da base e da altura no construtor, enquanto a segunda deve receber a posição x, y, e o tamanho do lado. Seus construtores devem invocar o construtor da superclasse Quadriláterio fazendo a devida passagem dos valores corretos par cada um dos lados do Quadriláterio. O método de cálculo da área deve ser implementado;
* Uma classe para representar Círculo que é uma subclasse de FormaGeométrica. Seu construtor deve receber o tamanho do raio. Os métodos de cálculo de perímetro e área do círculo devem ser implementados.

### Fórmulas para o cálculo da área e perímetro:

|Forma     |   Área     |    Perímetro               |
|----------|------------|----------------------------|
|quadrado  |    lado²   | 4lado                      |
|retângulo |base*altura | 2base + 2altura            |
|círculo   |π*raio²     |2π * raio (π = 3,1415927)   |
|triângulo |(√3*lado²)/4| 3lado                      |


*Nota: A raiz quadrada em Java está disponível na biblioteca java.lang.Math, no método Math.sqrt().*

# Questão 3
Com base na questão anterior, crie uma classe chamada GerenciadorFormasGeométricas, no pacote de negócio, para manipular os polígonos. Essa classe deve conter uma lista de Formas Geométricas armazenadas em um List. Ainda, ela deve conter métodos para inserir as Formas Geométricas na lista.

# Questão 4
Com base na questão anterior, crie uma classe chamada Principal, no pacote de apresentação, que terá o método main() e o menu de opções de criação das Formas Geométricas pelo usuário do sistema. Além dos métodos para cadastro das Formas Geométricas, essa classe deve conter também um método polimórfico chamado imprimirGeometria(FormaGeométrica f) que recebe uma forma geométrica como parâmetro e imprime a sua área e perímetro na tela.

# Questão 5
Crie o diagrama de classes do programa que manipula formas geométricas.