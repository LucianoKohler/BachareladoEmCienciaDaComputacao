package dados;

import exceptions.NomeInvalidoException;

public class Video extends Arquivo {
  private Qualidade qualidade;

  public Video(String nome, Qualidade qualidade) throws NomeInvalidoException{
    super(nome, ".mp4");
    this.qualidade = qualidade;
  }

  public Qualidade getQualidade() {
    return qualidade;
  }
  public void setQualidade(Qualidade qualidade) {
    this.qualidade = qualidade;
  }

  @Override
  public String toString(){
    return this.getNome() + this.getExtensao() + "\n\tQualidade: " + this.qualidade;
  }
}
