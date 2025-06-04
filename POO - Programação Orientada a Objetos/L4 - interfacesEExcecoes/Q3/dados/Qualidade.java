package dados;

public enum Qualidade {

  P240("240p"),
  P360("360p"),
  P720("720p"),
  P1080("1080p");

  public String qualidade;

  private Qualidade(String qualidade){
    this.qualidade = qualidade;
 }

 public String getQualidade(){
  return qualidade;
 }
 
}