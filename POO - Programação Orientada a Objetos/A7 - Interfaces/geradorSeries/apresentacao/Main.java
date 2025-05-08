
package apresentacao;
import dados.*;

public class Main {
  public static void main(String[] args){
    int qtd = 3;
    Naturais naturais = new Naturais(qtd);
    Abundantes abundantes = new Abundantes(qtd);
    Fatoriais fatoriais = new Fatoriais(qtd);
    Quadrados quadrados = new Quadrados(qtd);
    Primos primos = new Primos(qtd);
    Perfeitos perfeitos = new Perfeitos(qtd);
    Fibonacci fibonacci = new Fibonacci(qtd);

    // Edit 1: No enunciado, o professor pediu para gerar 10 números, porém, como os números perfeitos crescem extremamente rápido,
    // nem mesmo um long pode conter seus valores, então deixarei o valor como 5 para não gerar números muito grandes.

    // Edit 2: Mesmo diminuindo a quantidade de números, algum valor extrapola o limite do long, então deixarei apenas 3 valores.
    naturais.gerar(qtd);
    abundantes.gerar(qtd);
    fatoriais.gerar(qtd);
    quadrados.gerar(qtd);
    primos.gerar(qtd);
    perfeitos.gerar(qtd);
    fibonacci.gerar(qtd);

    // Mostra os valores
    System.out.println("Números naturais gerados:");
    for (int i = 0; i < qtd && i < naturais.getSequencia().length; i++) {
      System.out.println(naturais.getSequencia()[i]);
    }

    System.out.println("Números abundantes gerados:");
    for (int i = 0; i < qtd && i < abundantes.getSequencia().length; i++) {
      System.out.println(abundantes.getSequencia()[i]);
    }

    System.out.println("Números fatoriais gerados:");
    for (int i = 0; i < qtd && i < fatoriais.getSequencia().length; i++) {
      System.out.println(fatoriais.getSequencia()[i]);
    }
    
    System.out.println("Números quadrados gerados:");
    for (int i = 0; i < qtd && i < quadrados.getSequencia().length; i++) {
      System.out.println(quadrados.getSequencia()[i]);
    }

    System.out.println("Números primos gerados:");
    for (int i = 0; i < qtd && i < primos.getSequencia().length; i++) {
      System.out.println(primos.getSequencia()[i]);
    }

    System.out.println("Números perfeitos gerados:");
    for (int i = 0; i < qtd && i < perfeitos.getSequencia().length; i++) {
      System.out.println(perfeitos.getSequencia()[i]);
    }

    System.out.println("Sequência fibonacci gerada:");
    for (int i = 0; i < qtd && i < fibonacci.getSequencia().length; i++) {
      System.out.println(fibonacci.getSequencia()[i]);
    }

    System.out.println("DADOS SOBRE AS SEQUÊNCIAS GERADAS:");
    System.out.println("=========================================");
    System.out.println("Somatório:");
    System.out.println("Números naturais: " + naturais.somatorio());
    System.out.println("Números abundantes: " + abundantes.somatorio());
    System.out.println("Números fatoriais: " + fatoriais.somatorio());
    System.out.println("Números quadrados: " + quadrados.somatorio());
    System.out.println("Números primos: " + primos.somatorio());
    System.out.println("Números perfeitos: " + perfeitos.somatorio());
    System.out.println("Sequência fibonacci: " + fibonacci.somatorio());
    
    System.out.println("=========================================");
    System.out.println("Média Aritmética:");
    System.out.println("Números naturais: " + naturais.mediaAritmetica());
    System.out.println("Números abundantes: " + abundantes.mediaAritmetica());
    System.out.println("Números fatoriais: " + fatoriais.mediaAritmetica());
    System.out.println("Números quadrados: " + quadrados.mediaAritmetica());
    System.out.println("Números primos: " + primos.mediaAritmetica());
    System.out.println("Números perfeitos: " + perfeitos.mediaAritmetica());
    System.out.println("Sequência fibonacci: " + fibonacci.mediaAritmetica());
    
    System.out.println("=========================================");
    System.out.println("Média Geométrica:");
    System.out.println("Números naturais: " + naturais.mediaGeometrica());
    System.out.println("Números abundantes: " + abundantes.mediaGeometrica());
    System.out.println("Números fatoriais: " + fatoriais.mediaGeometrica());
    System.out.println("Números quadrados: " + quadrados.mediaGeometrica());
    System.out.println("Números primos: " + primos.mediaGeometrica());
    System.out.println("Números perfeitos: " + perfeitos.mediaGeometrica());
    System.out.println("Sequência fibonacci: " + fibonacci.mediaGeometrica());
    
    System.out.println("=========================================");
    System.out.println("Variância:");
    System.out.println("Números naturais: " + naturais.variancia());
    System.out.println("Números abundantes: " + abundantes.variancia());
    System.out.println("Números fatoriais: " + fatoriais.variancia());
    System.out.println("Números quadrados: " + quadrados.variancia());
    System.out.println("Números primos: " + primos.variancia());
    System.out.println("Números perfeitos: " + perfeitos.variancia());
    System.out.println("Sequência fibonacci: " + fibonacci.variancia());
    
    System.out.println("=========================================");
    System.out.println("Desvio Padrão:");
    System.out.println("Números naturais: " + naturais.desvioPadrao());
    System.out.println("Números abundantes: " + abundantes.desvioPadrao());
    System.out.println("Números fatoriais: " + fatoriais.desvioPadrao());
    System.out.println("Números quadrados: " + quadrados.desvioPadrao());
    System.out.println("Números primos: " + primos.desvioPadrao());
    System.out.println("Números perfeitos: " + perfeitos.desvioPadrao());
    System.out.println("Sequência fibonacci: " + fibonacci.desvioPadrao());
    
    System.out.println("=========================================");
    System.out.println("Amplitude:");
    System.out.println("Números naturais: " + naturais.amplitude());
    System.out.println("Números abundantes: " + abundantes.amplitude());
    System.out.println("Números fatoriais: " + fatoriais.amplitude());
    System.out.println("Números quadrados: " + quadrados.amplitude());
    System.out.println("Números primos: " + primos.amplitude());
    System.out.println("Números perfeitos: " + perfeitos.amplitude());
    System.out.println("Sequência fibonacci: " + fibonacci.amplitude());
  }
}
