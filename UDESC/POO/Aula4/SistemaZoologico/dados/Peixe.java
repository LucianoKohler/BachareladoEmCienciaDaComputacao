package dados;

public class Peixe extends Animal {
  private float temperaturaIdeal;
  
  // Construtor
  public Peixe(String nome, String cor, String especie, int idade, float largura, float comprimento, float altura, float temperaturaIdeal) {
    super(nome, cor, especie, idade, largura, comprimento, altura);
    this.temperaturaIdeal = temperaturaIdeal;
  }

  public float getTemperaturaIdeal() {
    return temperaturaIdeal;
  }
  public void setTemperaturaIdeal(float temperaturaIdeal) {
    this.temperaturaIdeal = temperaturaIdeal;
  }

  @Override
  public float calculaEspacoOcupado(){
    return this.largura * this.comprimento * this.altura;
  }

}
