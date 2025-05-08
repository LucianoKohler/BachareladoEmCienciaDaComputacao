package dados;

public interface ISequencia {
  public long sortear(int qtd);
  public long somatorio(int qtd);
  public double mediaAritmetica(int qtd);
  public double mediaGeometrica(int qtd);
  public double variancia(int qtd);
  public double desvioPadrao(int qtd);
  public long amplitude(int qtd);
}