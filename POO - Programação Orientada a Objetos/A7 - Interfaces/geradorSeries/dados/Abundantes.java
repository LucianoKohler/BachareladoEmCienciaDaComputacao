package dados;

public class Abundantes extends Gerador {

  public Abundantes(int qtd) { super(qtd); }
  
  public void gerar(int qtd){
    int count = 0;
    int i = 1;
    while(count < qtd){
      if(isAbundante(i)){
        sequencia[count] = i;
        count++;
      }
      i++;
    }
  }

  private boolean isAbundante(int n){
    int soma = 0;
    for(int i = 1; i <= n/2; i++){
      if(n % i == 0){
        soma += i;
      }
    }
    if(soma > n){
      return true;
    }else{
      return false;
    }
  }
}
