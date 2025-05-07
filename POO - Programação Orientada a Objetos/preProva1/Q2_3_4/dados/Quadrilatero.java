package dados;

public abstract class Quadrilatero extends FormaGeometrica{
  protected double l1;
  protected double l2;
  protected double l3;
  protected double l4;

  public Quadrilatero(double xPos, double yPos, double l1, double l2, double l3, double l4) {
    super(xPos, yPos);
    this.l1 = l1;
    this.l2 = l2;
    this.l3 = l3;
    this.l4 = l4;
  }

  public abstract double calcularArea();

  public double calcularPerimetro() {
    return l1 + l2 + l3 + l4;
  };
}
