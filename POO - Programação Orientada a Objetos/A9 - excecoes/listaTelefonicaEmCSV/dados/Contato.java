package dados;

public class Contato {
  private String nome;
  private String telefone;

  // construtor
  public Contato(String nome, String telefone) {
    this.nome = nome;
    this.telefone = telefone;
  }

  public Contato() {}

  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getNome() {
    return nome;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
  public String getTelefone() {
    return telefone;
  }

  public String toString() {
    return "Nome: " + nome + ", Telefone: " + telefone;
  }

  public boolean equals(Object object) {
    Contato c = (Contato) object;
    return c.getTelefone() == this.telefone;
  }
}
