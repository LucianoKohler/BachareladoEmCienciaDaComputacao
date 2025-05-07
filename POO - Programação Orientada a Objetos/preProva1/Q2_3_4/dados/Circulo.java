package dados;

public class Circulo extends FormaGeometrica {
  private double tamRaio;

  public Circulo (double xPos, double yPos, double tamRaio) {
    super(xPos, yPos);
    this.tamRaio = tamRaio;
  }

  public double calcularArea(){
    return 3.1415927 * tamRaio * tamRaio;
  }
  public double calcularPerimetro(){
    return 2 * 3.1415927 * tamRaio;
  }
}
