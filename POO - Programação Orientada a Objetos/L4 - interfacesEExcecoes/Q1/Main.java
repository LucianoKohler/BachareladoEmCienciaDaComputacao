import java.util.ArrayList;

public class Main {
  public static void main(String args[]){
    ArrayList<IOperacaoInteira> operacoes = new ArrayList<>();
    operacoes.add(new Soma());
    operacoes.add(new Multiplicacao());
    operacoes.add(new Mod());
    operacoes.add(new MDC());
    
    // Gerando valores aleatórios para cada operação e printando o resultado
    for (IOperacaoInteira operacao : operacoes) {
      int v1 = (int) (Math.random() * 100); // 0 a 99
      int v2 = (int) (Math.random() * 100); // 0 a 99
      System.out.println("Operação: " + operacao.getClass().getSimpleName()); // getClass retorna operacoes.operacao, e getSimpleName retorna apenas o nome da operacao
      System.out.println("Valores: " + v1 + ", " + v2);
      System.out.println("Resultado: " + operacao.executar(v1, v2));
      System.out.println();
    }
  }
}
