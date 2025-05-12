package formas;

public class Losango extends FormaGeometrica {
  public Losango(int d, int D) {
    super(d, D);
  }

  public void setd(int d) {
    this.medida1 = d;
  }

  public void setD(int D) {
    this.medida2 = D;
  }

  public double calculaArea() {
    return (medida1 * medida2) / 2.0;
  }

  public double calculaPerimetro() {
    double lado = Math.sqrt(Math.pow(medida1 / 2.0, 2) + Math.pow(medida2 / 2.0, 2));
    return 4 * lado;
  }

  public String toString() {
    return "Losango:\nDiagonal maior: " + medida1 + ", Diagonal menor: " + medida2 + ", área: " + calculaArea() + ", perímetro: " + calculaPerimetro();
  }
  
}
