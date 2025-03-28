package dados;

public class Episodio {
  private String nome;
  private Serie serie;
  private int duracao; // em minutos
  private int episodio;
  private int temporada;

  // Construtor
  public Episodio(String nome, Serie serie, int duracao, int episodio, int temporada) {
    this.nome = nome;
    this.serie = serie;
    this.duracao = duracao;
    this.episodio = episodio;
    this.temporada = temporada;
  }

  // Construtor vazio
  public Episodio() {}

  // Getters e Setters
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public Serie getSerie() {
    return serie;
  }
  public void setSerie(Serie serie) {
    this.serie = serie;
  }

  public int getDuracao() {
    return duracao;
  }
  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }

  public int getEpisodio() {
    return episodio;
  }
  public void setEpisodio(int episodio) {
    this.episodio = episodio;
  }

  public int getTemporada() {
    return temporada;
  }
  public void setTemporada(int temporada) {
    this.temporada = temporada;
  }

  // Outros métodos
  public boolean equals(Object o) {
    if(o instanceof Episodio){
      Episodio e = (Episodio) o;
      if(e.getNome().equals(nome)){
        return true;
      }
    }
    return false;
  }

  public String toString() {
    String str = "";
    str += "Nome: " + nome + "\n";
    str += "Série: " + serie.getNome() + "\n";
    str += "Duração: " + duracao + " minutos\n";
    str += "Episódio: " + episodio + "\n";
    str += "Temporada: " + temporada + "\n";
    return str;
  }
}
