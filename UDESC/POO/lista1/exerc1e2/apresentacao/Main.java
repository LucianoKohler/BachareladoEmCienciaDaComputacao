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
    
    // Com o construtor vazio:
    Filme Frozen = new Filme();
    Frozen.setNome("Frozen - Uma Aventura Congelante");
    Frozen.setGenero("Animação");
    Frozen.setDataLancamento("27/11/2013");
    Frozen.setDuracao(110);

    // Criando uma série com episodios:
    Serie gameOfThrones = new Serie("Game of Thrones", "Fantasia", "17/04/2011");
    Episodio episodio1 = new Episodio("Winter is Coming", gameOfThrones, 60, 1, 1);

    // Adicionando o novo episódio à série:
    gameOfThrones.adicionarEpisodio(episodio1);
  
    // Criando uma conta:
    Conta c1 = new Conta("Joaozinho", "joao123", "joao@gmail.com", "01/01/2000", true);
    
    // Criando uma estréia:
    Estreia estreia1 = new Estreia(harryPotter, "20/11/2001", "20:00");



    // Exibindo informações:
    System.out.println("FILMES NO CATÁLOGO:");
    System.out.println(harryPotter.toString() + "\n");
    System.out.println(Frozen.toString() + "\n");
    System.out.println("===========================\n");
    System.out.println("SÉRIES NO CATÁLOGO:");
    System.out.println(gameOfThrones.toString());
    System.out.println("===========================\n");
    System.out.println("ESTREIAS EM BREVE:");
    System.out.println(estreia1.toString() + "\n");
    System.out.println("===========================\n");
    System.out.println("CONTAS CADASTRADAS:");
    System.out.println(c1.toString() + "\n");
  }
}
