package dados;

public class Quadrado extends Quadrilatero {
  private double tamanhoLado;

  public Quadrado(double xPos, double yPos, double l1, double l2, double l3, double l4, double tamanhoLado) {
    super(xPos, yPos, l1, l2, l3, l4);
    this.tamanhoLado = tamanhoLado;
  }

  public double calcularArea(){
    return tamanhoLado*tamanhoLado;
  }
}
