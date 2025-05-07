package dados;

public class Monografia extends Item {
  private static final int tempoEmprestimo = 30;

  public Monografia(int idItem, String titulo, String nomeAutor, int ano) {
    super(idItem, titulo, nomeAutor, ano);
  }
  
  public void emprestar() {
    if (this.estaEmprestado()) {
      System.out.println("Item já emprestado.");
      return;
    }
      this.emprestado = true;
  }

  public String toString() {
    return "Monografia [idItem=" + this.getIdItem() + ", titulo=" + this.getTitulo() + ", nomeAutor=" + this.getNomeAutor() + ", ano=" + this.getAno() + ", emprestado=" + this.estaEmprestado() + "]";
  }
}
