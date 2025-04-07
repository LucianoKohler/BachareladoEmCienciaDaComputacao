package dados;

public class Imobiliaria {
  private String nome;
  private Imovel imoveis[] = new Imovel[10];
  private int quantImoveis;

  // Construtor
  public Imobiliaria(String nome) {
    this.nome = nome;
  }

  // Construtor vazio
  public Imobiliaria() {}

  // Getters e Setters
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public Imovel[] getImoveis() {
    return imoveis;
  }

  public void adicionarImovel(Imovel imovel) {
    if(quantImoveis < imoveis.length) {
      imoveis[quantImoveis] = imovel;
      quantImoveis++;
      ordenarImoveisPorPreco(imoveis); // Já deixando os imoveis ordenados a cada insercao
    } else {
      System.out.println("Limite de imóveis atingido.");
    }
  }

  

  public String toString() {
    String str = "";
    str += "Imobiliária: " + nome + "\n";
    str += "Imóveis:\n";
    for(int i = 0; i < quantImoveis; i++) {
      str += imoveis[i].toString() + "\n";
    }
    return str;
  }

  public void ordenarImoveisPorPreco(Imovel[] imoveis){
      for(int i = 0; i < quantImoveis; i++){
        for(int j = 0; j < quantImoveis; j++){
          if(imoveis[j].getPreco() > imoveis[i].getPreco()){
            Imovel aux = this.imoveis[j];
            this.imoveis[j] = this.imoveis[i];
            this.imoveis[i] = aux;          
          }
        }
      }
  }

  public String filtrarPorArea(float area){
    // Lembrando que os imoveis já são ordenados ao inserir um novo imovel
    String str = "";
    for(int i = 0; i < quantImoveis; i++){
      if(imoveis[i].calcularArea() >= area){
        str += imoveis[i].toString() + "\n";
      }
    }
    return str;
  }
}
