package dados;
public class Perfeitos extends Gerador {

  public Perfeitos(int qtd) { super(qtd); }

  public void gerar(int qtd) {
    int count = 0;
    int num = 1;

    while (count < qtd) {
      if (isPerfeito(num)) {
        sequencia[count] = num;
        count++;
      }
      num++;
    }
  }

  private boolean isPerfeito(int n) {
    int soma = 0;
    for (int i = 1; i <= n / 2; i++) {
      if (n % i == 0) {
        soma += i;
      }
    }
    if(soma == n) {
      return true;
    } else {
      return false;
    }
  }
  
}
