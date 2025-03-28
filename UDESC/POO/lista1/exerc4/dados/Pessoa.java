package dados;

public class Pessoa {
  private String nome;
  private int idade;

  // Construtor
  public Pessoa(String nome, int idade) {
    this.nome = nome;
    this.idade = idade;
  }

  // Construtor vazio
  public Pessoa() {}

  // Getters e Setters
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public int getIdade() {
    return idade;
  }
  public void setIdade(int idade) {
    this.idade = idade;
  }

  // Outros métodos
  public String toString() {
    String str = "";
    str += "Nome: " + nome + "\n";
    str += "Idade: " + idade + "\n";

    return str;
  }
  
}
