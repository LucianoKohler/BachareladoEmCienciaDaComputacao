package dados;

public class Aquario extends Viveiro {
  private float altura;
  private float temperatura;

  // Construtor
  public Aquario(String nome, float comprimento, float largura, float altura, float temperatura){
    super(nome, comprimento, largura);
    this.altura = altura;
    this.temperatura = temperatura;
  }

  public float getAltura() {
    return altura;
  }
  public void setAltura(float altura) {
    this.altura = altura;
  }

  public float getTemperatura() {
    return temperatura;
  }
  public void setTemperatura(float temperatura) {
    this.temperatura = temperatura;
  }

  @Override
  public boolean adicionarAnimal(Animal animal){
    if(!(animal instanceof Peixe)){
      System.out.println("Não é possível colocar um animal terrestre em um aquário");
      return false;
    }
    Peixe peixe = (Peixe) animal;
    if(peixe.getTemperaturaIdeal() > temperatura + 3 || peixe.getTemperaturaIdeal() < temperatura -3){
      System.out.println("O aquário não tem uma temperatura adequada para o peixe");
      return false;
    }else{
      this.animais[qtdAnimais] = animal;
      this.qtdAnimais++;
      System.out.println("Peixe alocado com sucesso");
      return true;
    }
  }

  @Override
  public float calculaEspaco(){
    return this.comprimento * this.largura * this.altura;
  }
}
