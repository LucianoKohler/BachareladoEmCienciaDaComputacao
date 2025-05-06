package dados;

public enum Combustivel {

  GASOLINA("Gasolina"),
  ALCOOL("Alcool"),
  DIESEL("Diesel"),
  GAS("Gas");
  
  private String tipo;

  private Combustivel(String tipo){
    this.tipo = tipo;
  }

  public String getTipo() {
    return tipo;
  }
}
