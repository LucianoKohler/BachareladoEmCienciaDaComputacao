package dados;

public class Filme {
  private String nome;
  private String genero;
  private String dataLancamento;
  private int duracao; // em minutos

  // Construtor
  public Filme(String nome, String genero, String dataLancamento, int duracao) {
    this.nome = nome;
    this.genero = genero;
    this.dataLancamento = dataLancamento;
    this.duracao = duracao;
  }

  // Construtor vazio
  public Filme() {}

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
  public int getDuracao() {
    return duracao;
  }
  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }

  // Outros métodos
  public String toString() {
    String str = "";
    str += "Nome: " + nome + "\n";
    str += "Gênero: " + genero + "\n";
    str += "Data de Lançamento: " + dataLancamento + "\n";
    str += "Duração: " + duracao + " minutos\n";
    return str;
  }

  public boolean equals(Object o) {
    if(o instanceof Filme){
      Filme c = (Filme) o;
      if(c.getNome().equals(nome)){
        return true;
      }
    }
    return false;
  }
}
