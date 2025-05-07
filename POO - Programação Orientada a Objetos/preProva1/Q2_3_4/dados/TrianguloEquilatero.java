package dados;

import java.lang.Math;

public class TrianguloEquilatero extends FormaGeometrica {
  private double tamLado;

  public TrianguloEquilatero(double xPos, double yPos, double tamLado){
    super(xPos, yPos);
    this.tamLado = tamLado;
  }

  public double calcularArea(){
    return (Math.sqrt(3) * tamLado * tamLado) / 4;
  }
  public double calcularPerimetro(){
    return 3 * tamLado;
  }
}
