package dados;

public class Cidade {
  private String nome;
  private String estado;

  // Construtor
  public Cidade(String nome, String estado){
    this.nome = nome;
    this.estado = estado;
  }

  // Gets e Sets
  public String getEstado() {
    return estado;
  }
  public void setEstado(String estado) {
    this.estado = estado;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  // Métodos
  public String toString(){
    return "Nome: " + this.nome + "; Estado: " + this.estado;
  }

  
}
