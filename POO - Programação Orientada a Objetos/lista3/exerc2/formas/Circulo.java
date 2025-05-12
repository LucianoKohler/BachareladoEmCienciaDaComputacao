package formas;

public class Circulo extends FormaGeometrica {
  public Circulo(int raio) {
    super(raio, 0);
  }

  public void setRaio(int raio) {
    this.medida1 = raio;
  }

  public double calculaArea() {
    return Math.PI * medida1 * medida1;
  }
  
  public double calculaPerimetro() {
    return 2 * Math.PI * medida1;
  }

  public String toString() {
    return "Círculo:\nRaio: " + medida1 + ", área: " + calculaArea() + ", perímetro: " + calculaPerimetro();
  }
}