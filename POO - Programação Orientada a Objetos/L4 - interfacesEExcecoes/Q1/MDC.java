public class MDC implements IOperacaoInteira {
  @Override
  public int executar(int v1, int v2) {
    if (v2 == 0) {
      return v1;
    }
    while (v2 != 0) {
      int temp = v2;
      v2 = v1 % v2;
      v1 = temp;
    }
    return v1;
  } 
}
