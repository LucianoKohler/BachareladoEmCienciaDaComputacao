package dados;

public class Aluno {
  private String nome;
  private int idade;
  private double[] notas = new double[5];

  // Construtor
  public Aluno(String nome, int idade, double n1, double n2, double n3, double n4, double n5) {
    this.nome = nome;
    this.idade = idade;
    this.notas[0] = n1;
    this.notas[1] = n2;
    this.notas[2] = n3;
    this.notas[3] = n4;
    this.notas[4] = n5;
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

  public double calcularMedia() {
    double soma = 0;
    for (int i = 0; i < notas.length; i++) {
      soma += notas[i];
    }
    return soma / notas.length;
  }

  public String toString() {
    String str = "";
    str += "Nome: " + nome + "\n";
    str += "Idade: " + idade + "\n";
    str += String.format("Média escolar: %.2f%n", calcularMedia());
    return str;
  }
}
