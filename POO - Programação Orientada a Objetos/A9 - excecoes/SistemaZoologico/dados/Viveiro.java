package dados;

public class Viveiro {
  protected String nome;
  protected float comprimento;
  protected float largura;
  protected Animal[] animais = new Animal[100];
  protected int qtdAnimais = 0;

  // Construtor
  public Viveiro(String nome, float comprimento, float largura) {
    this.nome = nome;
    this.comprimento = comprimento;
    this.largura = largura;
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public float getComprimento() {
    return comprimento;
  }
  public void setComprimento(float comprimento) {
    this.comprimento = comprimento;
  }

  public float getLargura() {
    return largura;
  }
  public void setLargura(float largura) {
    this.largura = largura;
  }

  public int getQtdAnimais() {
    return qtdAnimais;
  }
  
  public float calculaEspaco(){
    return this.comprimento * this.largura;
  }

  private float espacoOcupado(){
    float espaco = 0;
    for(int i = 0; i < qtdAnimais; i++){
      espaco += animais[i].calculaEspacoOcupado();
    }
    return espaco;
  }

  public float espacoDisponivel(){
    return this.calculaEspaco() - this.espacoOcupado();
  }

  public boolean adicionarAnimal(Animal animal){
    // Os animais apenas podem ser alocados no viveiro se a área disponível no for maior que 70% da área do animal. 
    if(this.espacoDisponivel() >= animal.calculaEspacoOcupado()*0.7){
      this.animais[qtdAnimais] = animal;
      this.qtdAnimais++;
      System.out.println("Animal alocado com sucesso.");
      return true;
    } else {
      System.out.println("Não há espaço suficiente para alocar o animal.");
      return false;
    }
  }

  public String toString(){
    String str = "";
    str+= "Nome: " + this.nome + "\n";
    str+= "Comprimento: " + this.comprimento + "cm\n";
    str+= "Largura: " + this.largura + "cm\n";
    return str;
  }

  public String mostrarAnimaisNoViveiro(){
    String str = "\n";
    for(int i = 0; i < qtdAnimais; i++){
      str+= "Animal " + (i+1) + ": \n" + animais[i].toString() + "\n";
    }
    return str;
  }
}
