package dados;

public class Quadrados extends Gerador {

  public Quadrados(int qtd) { super(qtd); }

  public void gerar(int qtd) {
    for (int i = 1; i <= qtd; i++) {
      sequencia[i-1] = i * i;
    }
  }
}
