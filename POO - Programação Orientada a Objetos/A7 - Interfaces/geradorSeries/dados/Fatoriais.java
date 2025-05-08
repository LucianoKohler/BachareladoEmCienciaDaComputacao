package dados;

public class Fatoriais extends Gerador {

  public Fatoriais(int qtd) { super(qtd); }

  public void gerar(int qtd) {
    for (int i = 1; i <= qtd; i++) {
      sequencia[i-1] = fatorial(i);
    }
  }

  // Função recursiva para não poluir a função gerar()
  private int fatorial(int n) {
    if (n == 0 || n == 1) {
      return 1;
    } else {
      return n * fatorial(n - 1);
    }
  }
}