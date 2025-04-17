package dados;

public class Bicicleta extends Veiculo{
  private int numeroMarchas;

  public int getNumeroMarchas() {
    return numeroMarchas;
  }
  public void setNumeroMarchas(int numeroMarchas) {
    this.numeroMarchas = numeroMarchas;
  }

  public String info() {
    return "\n Bicicleta:\nCor: " + this.getCor() + "\nNúmero de marchas: " + this.getNumeroMarchas() + "\n";
  }
}
