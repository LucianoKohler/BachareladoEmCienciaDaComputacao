package dados;

public class Naturais extends Gerador {
  
  public void gerar(int qtd){
    for(int i = 1; i <= qtd; i++){
      sequencia.add(i);
    }
  }
}
