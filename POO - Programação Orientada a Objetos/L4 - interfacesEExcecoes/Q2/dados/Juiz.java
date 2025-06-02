package dados;

import exceptions.pilhaCheiaException;

public class Juiz {
  String nome;
  Pilha<Processo> processos;

  public Juiz(String nome, int limite){
    this.nome = nome;
    this.processos = new Pilha<Processo>(limite);
  }

  public void cadastrarProcesso(Processo p) throws pilhaCheiaException {
    try{
      processos.inserir(p);
    } catch (pilhaCheiaException e){
      System.out.println(e.getMessage());
    }
  }

  public String getNome() {
    return nome;
  }
}
