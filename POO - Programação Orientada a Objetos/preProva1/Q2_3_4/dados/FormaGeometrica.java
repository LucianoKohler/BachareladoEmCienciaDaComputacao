package dados;

public abstract class FormaGeometrica {
  private double xPos;
  private double yPos;

  public FormaGeometrica(double xPos, double yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public double getXPos() {
    return xPos;
  }
  public double getYPos() {
    return yPos;
  }
  public void setXPos(double xPos) {
    this.xPos = xPos;
  }
  public void setYPos(double yPos) {
    this.yPos = yPos;
  }

  public abstract double calcularArea();
  public abstract double calcularPerimetro();
}