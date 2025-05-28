package dados;

import java.util.LinkedList;
import java.util.List;

public class CalculadoraEstatistica implements ICalculadora {
  private static CalculadoraEstatistica instance = null;
  private List<Integer> sequencia = new LinkedList<Integer>();

  private CalculadoraEstatistica(){};

  public static CalculadoraEstatistica getInstance(){
    if(instance == null){
      instance = new CalculadoraEstatistica();
    }
    return instance;
  }

  public void adicionarValor(int valor){
    this.sequencia.add(valor);
  }

  public List<Integer> getValores() {
    return sequencia;
  }

  public void setValores(List<Integer> valores){
    this.sequencia = valores;
  }

  public void limparValores(){
    this.sequencia.clear();
  }

  public int sortear(){
    int index = (int) (Math.random() * sequencia.size());
    return sequencia.get(index);
  }

  public long somatorio(){
    long soma = 0;
    for (int i = 0; i < sequencia.size(); i++) {
      soma += sequencia.get(i);
    }
    return soma;
  }

  // Auxiliar
  public long produtorio(){
    long soma = 0;
    for (int i = 0; i < sequencia.size(); i++) {
      soma *= sequencia.get(i);
    }
    return soma;
  }

  public double mediaGeometrica(){
    return Math.pow(produtorio(), 1.0 / (double) (sequencia.size()));
  }

  public double mediaAritmetica(){
    return somatorio() / (double) (sequencia.size());
  }

  public double variancia(){
    double media = mediaAritmetica();
    double somaQuadrados = 0.0;
    for (int i = 0; i < sequencia.size(); i++) {
      somaQuadrados += Math.pow(sequencia.get(i) - media, 2);
    }
    return somaQuadrados / (double) sequencia.size();
  }

  public double desvioPadrao(){
    return Math.sqrt(variancia());
  }

  public long amplitude(){
    long min = sequencia.get(0);
    long max = sequencia.get(0);
    for (int i = 1; i < sequencia.size(); i++) {
      if (sequencia.get(i) < min) {
        min = sequencia.get(i);
      }
      if (sequencia.get(i) > max) {
        max = sequencia.get(i);
      }
    }
    return max - min;
  }  


}
