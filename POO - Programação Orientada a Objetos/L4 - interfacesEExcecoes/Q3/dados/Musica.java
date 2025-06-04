package dados;

import exceptions.NomeInvalidoException;

public class Musica extends Arquivo {
  private int duracao;

  public Musica(String nome, int duracao) throws NomeInvalidoException{
    super(nome, ".mp3");
    this.duracao = duracao;
  }

  @Override
  public String toString(){
    return this.getNome() + this.getExtensao() + "\n\tDuração: " + this.duracao;
  }
}
