public class Multiplicacao extends Soma {
  @Override
  public int executar(int v1, int v2) {
    int resultado = 0;
    for (int i = 0; i < v2; i++) {
      resultado = super.executar(resultado, v1);
    }
    return resultado;
  }
}
