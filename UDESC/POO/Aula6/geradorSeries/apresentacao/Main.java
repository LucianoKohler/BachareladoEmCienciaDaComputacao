
package apresentacao;
import dados.*;

public class Main {
  public static void main(String[] args){
    Naturais naturais = new Naturais();
    Abundantes abundantes = new Abundantes();
    Fatoriais fatoriais = new Fatoriais();
    Quadrados quadrados = new Quadrados();
    Primos primos = new Primos();
    Perfeitos perfeitos = new Perfeitos();
    Fibonacci fibonacci = new Fibonacci();

    int qtd = 4; 
    naturais.gerar(qtd);
    abundantes.gerar(qtd);
    fatoriais.gerar(qtd);
    quadrados.gerar(qtd);
    primos.gerar(qtd);
    perfeitos.gerar(qtd);
    fibonacci.gerar(qtd);


    // Mostra os valores
    System.out.println("Números naturais gerados:");
    for (int i = 0; i < qtd && i < naturais.getSequencia().size(); i++) {
      System.out.println(naturais.getSequencia().get(i));
    }

    System.out.println("Números abundantes gerados:");
    for (int i = 0; i < qtd && i < abundantes.getSequencia().size(); i++) {
      System.out.println(abundantes.getSequencia().get(i));
    }

    System.out.println("Números fatoriais gerados:");
    for (int i = 0; i < qtd && i < fatoriais.getSequencia().size(); i++) {
      System.out.println(fatoriais.getSequencia().get(i));
    }
    
    System.out.println("Números quadrados gerados:");
    for (int i = 0; i < qtd && i < quadrados.getSequencia().size(); i++) {
      System.out.println(quadrados.getSequencia().get(i));
    }

    System.out.println("Números primos gerados:");
    for (int i = 0; i < qtd && i < primos.getSequencia().size(); i++) {
      System.out.println(primos.getSequencia().get(i));
    }

    System.out.println("Números perfeitos gerados:");
    for (int i = 0; i < qtd && i < perfeitos.getSequencia().size(); i++) {
      System.out.println(perfeitos.getSequencia().get(i));
    }

    System.out.println("Sequência fibonacci gerada:");
    for (int i = 0; i < qtd && i < fibonacci.getSequencia().size(); i++) {
      System.out.println(fibonacci.getSequencia().get(i));
    }

  }
}
