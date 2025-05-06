package apresentacao;

import dados.Filme;
import dados.Estreia;
import dados.Serie;
import dados.Episodio;
import dados.Conta;

// IMPORTANTE:
// A mostra de dados é bem extensa, recomendo aumentar o tamnaho do terminal e diminuir a fonte dele para caber o máximo de informações

public class Main {
  public static void main(String[] args) {
    // Criando filmes:

    // Com o construtor completo:
    Filme harryPotter = new Filme("Harry Potter e a Pedra Filosofal", "Fantasia", "20/11/2001", 140);
    
    // Com o construtor vazio e utilizando os SETS:
    Filme frozen = new Filme();
    frozen.setNome("Frozen - Uma Aventura Congelante");
    frozen.setGenero("Animação");
    frozen.setDataLancamento("27/11/2013");
    frozen.setDuracao(110);

    // Criando uma série com episodios:
    Serie gameOfThrones = new Serie("Game of Thrones", "Fantasia", "17/04/2011");
    Episodio episodio1 = new Episodio("O Inicio", gameOfThrones, 60, 1, 1);
    Episodio episodio2 = new Episodio("A guerra do Paraguai", gameOfThrones, 50, 2, 1);
    Episodio episodio3 = new Episodio("O protagonista morre!", gameOfThrones, 55, 3, 1);

    // Adicionando o novo episódio à série:
    gameOfThrones.adicionarEpisodio(episodio1);
    gameOfThrones.adicionarEpisodio(episodio2);
    gameOfThrones.adicionarEpisodio(episodio3);
  
    // Criando contas:
    Conta c1 = new Conta("Joaozinho", "joao123", "joao@gmail.com", "01/01/2000", true);
    Conta c2 = new Conta("Herbert", "pindamonhangama", "herbert@gmail.com", "22/11/2010", false);
    
    // Criando estréias:
    Estreia estreia1 = new Estreia(harryPotter, "20/11/2001", "20:00");
    Estreia estreia2 = new Estreia(frozen, "12/02/2001", "15:15");

    // Exibindo informações:
    System.out.println("FILMES NO CATÁLOGO:");
    System.out.println(harryPotter.toString() + "\n");
    System.out.println(frozen.toString() + "\n");
    System.out.println("===========================\n");
    System.out.println("SÉRIES NO CATÁLOGO:");
    System.out.println(gameOfThrones.toString());
    System.out.println("===========================\n");
    System.out.println("ESTREIAS EM BREVE:");
    System.out.println(estreia1.toString() + "\n");
    System.out.println(estreia2.toString() + "\n");
    System.out.println("===========================\n");
    System.out.println("CONTAS CADASTRADAS:");
    System.out.println(c1.toString() + "\n");
    System.out.println(c2.toString() + "\n");
    System.out.println("===========================\n");

    // USANDO GETS
    System.out.println("Usando Gets em um filme:");
    System.out.println(harryPotter.getNome());
    System.out.println(harryPotter.getGenero());
    System.out.println(harryPotter.getDataLancamento());
    System.out.println(harryPotter.getDuracao());
  }
}
