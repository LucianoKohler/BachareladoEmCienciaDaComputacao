package dados;

import exceptions.NomeInvalidoException;

public class Documento extends Arquivo {
  private String texto;

  public Documento(String nome, String texto) throws NomeInvalidoException{
    super(nome, ".txt");
    this.texto = texto;
  }

  public String getTexto() {
    return texto;
  }
  public void setTexto(String texto) {
    this.texto = texto;
  }

  @Override
  public String toString(){
    return this.getNome() + this.getExtensao();
  }
}
