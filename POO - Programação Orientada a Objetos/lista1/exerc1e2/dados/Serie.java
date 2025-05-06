package dados;

public class Serie {
  private String nome;
  private String genero;
  private String dataLancamento;
  private Episodio[] episodios = new Episodio[50];
  private int quantEpisodios;

  // Construtor
  public Serie(String nome, String genero, String dataLancamento) {
    this.nome = nome;
    this.genero = genero;
    this.dataLancamento = dataLancamento;
  }

  // Construtor vazio
  public Serie() {}

  // Getters e Setters
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String getGenero() {
    return genero;
  }
  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getDataLancamento() {
    return dataLancamento;
  }
  public void setDataLancamento(String dataLancamento) {
    this.dataLancamento = dataLancamento;
  }

  public Episodio[] getEpisodios() {
    return episodios;
  }
  public void adicionarEpisodio(Episodio episodio) {
    if(quantEpisodios < episodios.length) {
      episodios[quantEpisodios] = episodio;
      quantEpisodios++;
    } else {
      System.out.println("Limite de episódios atingido.");
    }
  }

  public int getQuantEpisodios() {
    return quantEpisodios;
  }

  // Outros métodos
  public String toString() {
    String str = "";
    str += "Nome: " + nome + "\n";
    str += "Gênero: " + genero + "\n";
    str += "Data de Lançamento: " + dataLancamento + "\n";
    str += "Episódios: \n\n";
    for(int i = 0; i < quantEpisodios; i++) {
      str += episodios[i].toString() + "\n";
    }
    return str;
  }

  public boolean equals(Object o) {
    if(o instanceof Serie){
      Serie s = (Serie) o;
      if(s.getNome().equals(nome)){
        return true;
      }
    }
    return false;
  }
}
