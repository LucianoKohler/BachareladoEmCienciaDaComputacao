package apresentacao;

import dados.Juiz;
import negocio.SistemaProcessos;

public class Main {
  public static void main(String[] args) {
    SistemaProcessos sistema = new SistemaProcessos();
    Juiz j1 = new Juiz("Pedro Henrique de Melo", 5);
    Juiz j2 = new Juiz("Maria Bernaditta II", 5);
    Juiz j3 = new Juiz("Saul Goodman", 5);
    sistema.cadastrarJuiz(j1);
    sistema.cadastrarJuiz(j2);
    sistema.cadastrarJuiz(j3);

    // Gerando 18 processos 
    for(int i = 1; i <=18; i++){
      String nomeProcesso = "Processo " + i;
      sistema.cadastrarProcesso(new dados.Processo(nomeProcesso));
    }

    try {
      sistema.distribuirProcessos();
    } catch (exceptions.processoSemJuizException e) {
      System.out.println(e.getMessage());
    }
  }
}
