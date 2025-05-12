public class Pessoa {
  private String nome;
  private int idade;
  private String cpf;
  private String cidade;

  // Construtor
  public Pessoa(String nome, int idade, String cpf, String cidade) {
    this.nome = nome;
    this.idade = idade;
    this.cpf = cpf;
    this.cidade = cidade;
  }
  
  // Gets e Sets
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

  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getCidade() {
    return cidade;
  }
  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String toString() {
    return "Nome: " + nome + ", Idade: " + idade + ", CPF: " + cpf + ", Cidade: " + cidade;
  }
}
