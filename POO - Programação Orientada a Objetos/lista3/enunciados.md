# Exercício 1:

Implemente em Java a classe abstrata **Animal** que possui um atributo nome e um método abstrato **String emitirSom()**. Tal método deve retornar uma *String*. Crie outras três classes que estendam Animal e implementam o método **String emitirSom()** de acordo com o som que o animal emite. Em seguida, crie um método **main()** em uma classe própria para ele e instancie ao menos dois objetos de cada uma das classes. Exemplo de som emitido por um cão chamado Rex: *“Rex: Au-au”*.

# Exercício 2:

implemente as classes **FormaGeometrica (abstrata), TrianguloEquilatero, Losango e Circulo**.

A classe abstrata **FormaGeometrica** possui duas medidas, das quais as classes filhas interpretam de acordo com as medidas que elas utilizam, setando tais valores por meio dos setters específicos nas classes filhas. A classe **TrianguloEquilatero** possui um atributo lado. Para alterar tal valor, a classe possui um setter, o qual modifica o atributo presente na superclasse.

faça métodos para calcular o perímetro e a área das formas, e o método **toString()**, depois, gere dois objetos de cada classe no método **main()**

# Exercício 3:

Faça um programa que crie gráfos e dígrafos por meio de uma **List&lt;List&lt;int>>**, onde cada elemento representa uma conexão de elemento para elemento no grafo, note que a matriz do grafo é simétrica, pois toda conexão vai e volta em um grafo, porém, no dígrafo, essas conexões podem ou não serem únicas.

# Exercício 4:

Em Python, faça uma classe pessoa, que depois, irá se extender entre:

* **Aluno**, que possui um nome e 5 notas
* **Professor**, que possui um nome e um salário

Faça um método para calcular a média do aluno e o método **&#95;&#95;repr__()**, que printa as informações do aluno e se o mesmo passou de ano ou está de exame (média para passar = 7). Similarmente, faça o mesmo método **&#95;&#95;repr__()** para o professor, mostrando dessa vez, seu nome e salário.

Por fim, instancie dois professores e cinco alunos, e mostre suas informações no terminal.


