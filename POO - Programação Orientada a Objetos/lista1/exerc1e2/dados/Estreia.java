package dados;

public class Estreia {
  private Filme filme;
  private String dataEstreia;
  private String horaEstreia;

  // Construtor
  public Estreia(Filme filme, String dataEstreia, String horaEstreia) {
    this.filme = filme;
    this.dataEstreia = dataEstreia;
    this.horaEstreia = horaEstreia;
  }

  // Construtor vazio
  public Estreia() {}

  // Getters e Setters
  public Filme getFilme() {
    return filme;
  }
  public void setFilme(Filme filme) {
    this.filme = filme;
  }

  public String getDataEstreia() {
    return dataEstreia;
  }
  public void setDataEstreia(String dataEstreia) {
    this.dataEstreia = dataEstreia;
  }

  public String getHoraEstreia() {
    return horaEstreia;
  }
  public void setHoraEstreia(String horaEstreia) {
    this.horaEstreia = horaEstreia;
  }

  // Outros métodos
  public boolean equals(Object o) {
    if(o instanceof Estreia){
      Estreia e = (Estreia) o;
      return this.filme.equals(e.getFilme()) && this.dataEstreia.equals(e.getDataEstreia()) && this.horaEstreia.equals(e.getHoraEstreia());
    }
    return false;
  }

  public String toString() {
    String str = "";
    str += "Filme: " + filme.getNome() + "\n";
    str += "Data de Estreia: " + dataEstreia + "\n";
    str += "Hora de Estreia: " + horaEstreia + "\n";
    str += "Duração: " + filme.getDuracao() + " minutos" + "\n";
    str += "Gênero: " + filme.getGenero() + "\n";
    return str;
  }
}
