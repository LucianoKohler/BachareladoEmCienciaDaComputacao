package dados;

public class Primos extends Gerador {

  public Primos(int qtd) { super(qtd); }
  
  public void gerar(int qtd) {
    int count = 0;
    int num = 2; 

    while (count < qtd) {
      if (isPrimo(num)) {
        sequencia[count] = num;
        count++;
      }
      num++;
    }
  }

  private boolean isPrimo(int n) {
    if (n <= 1) return false;
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) return false;
    }
    return true;
  }

}

