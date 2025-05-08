package dados;

public class Naturais extends Gerador {

  public Naturais(int qtd) { super(qtd); }
  
  public void gerar(int qtd){
    for(int i = 1; i <= qtd; i++){
      sequencia[i-1] = i;
    }
  }
}
