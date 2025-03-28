package dados;

public class Imovel {
  private float largura;
  private float comprimento;
  private float preco;

  // Construtor
  public Imovel(float largura, float comprimento, float preco) {
    this.largura = largura;
    this.comprimento = comprimento;
    this.preco = preco;
  }

  // Construtor vazio
  public Imovel() {}

  // Getters e Setters
  public float getLargura() {
    return largura;
  }
  public void setLargura(float largura) {
    this.largura = largura;
  }

  public float getComprimento() {
    return comprimento;
  }
  public void setComprimento(float comprimento) {
    this.comprimento = comprimento;
  }

  public float getPreco() {
    return preco;
  }
  public void setPreco(float preco) {
    this.preco = preco;
  }

  // Outros métodos
  public float calcularArea() {
    return largura * comprimento;
  }

  public String toString() {
    String str = "";
    str += "Largura: " + largura + "\n";
    str += "Comprimento: " + comprimento + "\n";
    str += "Preço: " + preco + "\n";

    return str;
  }
}
