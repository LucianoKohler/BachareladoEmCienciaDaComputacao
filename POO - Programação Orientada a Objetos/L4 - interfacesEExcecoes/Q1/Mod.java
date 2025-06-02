public class Mod implements IOperacaoInteira {
  @Override
  public int executar(int v1, int v2) {
    if (v2 == 0) {
      System.out.println("Divisão por zero não é permitida.");
    }
    int sinal = (v1 < 0) ? -1 : 1;
    int absV1 = (v1 < 0) ? -v1 : v1; 
    int absV2 = (v2 < 0) ? -v2 : v2;
    while (absV1 >= absV2) {
      absV1 -= absV2;
    }
    return sinal * absV1;
  }
  
}
