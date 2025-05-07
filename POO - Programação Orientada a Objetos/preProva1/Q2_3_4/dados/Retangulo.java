package dados;

public class Retangulo extends Quadrilatero {
  private double tamMenorLado;
  private double tamMaiorLado;

  public Retangulo(double xPos, double yPos, double l1, double l2, double l3, double l4, double tamMenorLado, double tamMaiorLado) {
    super(xPos, yPos, l1, l2, l3, l4);
    this.tamMenorLado = tamMenorLado;
    this.tamMaiorLado = tamMaiorLado;
  }

  public double calcularArea(){
    return tamMaiorLado * tamMenorLado;
  }
}
