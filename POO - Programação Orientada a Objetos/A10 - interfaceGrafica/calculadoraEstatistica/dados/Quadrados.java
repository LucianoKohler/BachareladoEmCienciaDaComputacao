package dados;

public class Quadrados extends Gerador {
  public void gerar(int qtd) {
    for (int i = 1; i <= qtd; i++) {
      sequencia.add(i * i);
    }
  }
}
