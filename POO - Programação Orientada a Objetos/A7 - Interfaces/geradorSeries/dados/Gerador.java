package dados;

public abstract class Gerador implements ISequencia {
  protected long[] sequencia;

  // Construtor
  public Gerador(int qtd) {
    sequencia = new long[qtd];
  }

  public abstract void gerar(int qtd);

  public long[] getSequencia() {
    return sequencia;
  }

  public long sortear(){
    int index = (int) (Math.random() * sequencia.length);
    return sequencia[index];
  }

  public long somatorio(){
    long soma = 0;
    for (int i = 0; i < sequencia.length; i++) {
      soma += sequencia[i];
    }
    return soma;
  }

  public double mediaAritmetica(){
    return somatorio() / (Double) sequencia.length;
  }

  public double mediaGeometrica(){
    double produto = 1.0;
    for (int i = 0; i < sequencia.length; i++) {
      produto *= sequencia[i];
    }
    return Math.pow(produto, 1.0 / sequencia.length);
  }

  public double variancia(){
    double media = mediaAritmetica();
    double somaQuadrados = 0.0;
    for (int i = 0; i < sequencia.length; i++) {
      somaQuadrados += Math.pow(sequencia[i] - media, 2);
    }
    return somaQuadrados / (Double) sequencia.length;
  }

  public double desvioPadrao(){
    return Math.sqrt(variancia());
  }
  
  public long amplitude(){
    long min = sequencia[0];
    long max = sequencia[0];
    for (int i = 1; i < sequencia.length; i++) {
      if (sequencia[i] < min) {
        min = sequencia[i];
      }
      if (sequencia[i] > max) {
        max = sequencia[i];
      }
    }
    return max - min;
  }

}