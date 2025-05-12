public enum Quadrante {
  PRIMEIRO("Primeiro"),
  SEGUNDO("Segundo"),
  TERCEIRO("Terceiro"),
  QUARTO("Quarto");

  private String quad;

  private Quadrante(String quad) {
    this.quad = quad;
  }

  public String getQuadrante() {
    return quad;
  }
}
