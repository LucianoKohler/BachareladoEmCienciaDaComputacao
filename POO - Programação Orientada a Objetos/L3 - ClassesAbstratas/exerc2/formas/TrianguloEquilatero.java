package formas;

public class TrianguloEquilatero extends FormaGeometrica {
  public TrianguloEquilatero(int lado) {
    super(lado, 0);
  }
  
  public void setLado(int lado) {
    this.medida1 = lado;
  }

  public double calculaArea() {
    return (Math.sqrt(3) / 4) * medida1 * medida1;
  }

  public double calculaPerimetro() {
    return 3 * medida1;
  }

  public String toString() {
    return "Triângulo Equilátero:\nLado: " + medida1 + ", área: " + calculaArea() + ", perímetro: " + calculaPerimetro();
  }
}
