package dados;

public class Fibonacci extends Gerador{
  public void gerar(int qtd){
    for(int i = 0; i < qtd; i++){
      sequencia.add(fibonacci(i));
  }
}

  public int fibonacci(int n) {
    if (n <= 1) {
      return n;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }
}
