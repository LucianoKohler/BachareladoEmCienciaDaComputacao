package formas;

public abstract class FormaGeometrica {
  protected int medida1;
  protected int medida2;

  public FormaGeometrica(int medida1, int medida2) {
    this.medida1 = medida1;
    this.medida2 = medida2;
  }

  public abstract double calculaArea();
  public abstract double calculaPerimetro();
}
