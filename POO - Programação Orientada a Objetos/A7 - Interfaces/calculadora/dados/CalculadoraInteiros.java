package dados;

public class CalculadoraInteiros implements IOperacoesBasicas<Integer> {
  public Integer soma(Integer op1, Integer op2){ return op1+op2; }
  public Integer subtracao(Integer op1, Integer op2){ return op1-op2; }
}
