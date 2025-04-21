import java.util.ArrayList;

public class Matriz {
  private int n; // N° de linhas
  private int m; // N° de colunas
  private ArrayList<ArrayList<Integer>> matriz;

  // Construtor
  public Matriz(int n, int m) {
    this.n = n;
    this.m = m;
    matriz = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      ArrayList<Integer> linha = new ArrayList<>();
      for (int j = 0; j < m; j++) {
        linha.add(0);
      }
      matriz.add(linha);
    }
  }

  public int get(int li, int col){
    if(li > n || col > m || li < 0 || col < 0){
      System.out.println("Índices fora dos limites");
      return 0;
    }else{
      return matriz.get(li).get(col);
    }    
  }

  public boolean set(int obj, int li, int col){
    if(li > n || col > m || li < 0 || col < 0){
      System.out.println("Índices fora dos limites");
      return false;
    }else{
      matriz.get(li).set(col, obj);
      return true;
    }
  }
  
  public ArrayList<Integer> getLinha(int li){
    if(li > n || li < 0){
      System.out.println("Índices fora dos limites");
      return null;
    }else{
      return matriz.get(li-1); // Contando de 0 a 4, logo a 1° linha é a 0°
    }
  }

  public ArrayList<Integer> getColuna(int col){
    if(col > m || col < 0){
      System.out.println("Índices fora dos limites");
      return null;
    }else{
      ArrayList<Integer> coluna = new ArrayList<>();
      for(int i = 0; i < n; i++){
        coluna.add(matriz.get(i).get(col-1)); // Contando de 0 a 4, logo a 1° coluna é a 0°
      }
      return coluna;
    }
  }

  public ArrayList<Integer> getElementosQuadrante(Quadrante quadrante){
    // Definindo dimensões a se procurar os elementos
    int startLi;
    int endLi;
    int startCol;
    int endCol;
    switch (quadrante) {
      case PRIMEIRO:
        startLi = 0;
        startCol = 0;
        endLi = (int) Math.ceil(n/2.0);
        endCol = (int) Math.ceil(m/2.0);
        break;
    
      case SEGUNDO:
        startLi = 0;
        startCol = (int) Math.ceil(m/2.0);
        endLi = (int) Math.ceil(n/2.0);
        endCol = m;
        break;
        
        case TERCEIRO:
        startLi = (int) Math.ceil(n/2.0);
        startCol = 0;
        endLi = n;
        endCol = (int) Math.ceil(m/2.0);
        break;
        
        case QUARTO:
        startLi = (int) Math.ceil(n/2.0);
        startCol = (int) Math.ceil(m/2.0);
        endLi = n;
        endCol = m;
        break;

      default:
        System.out.println("Quadrante inválido, retornando matriz completa.");
        startLi = 0;
        endLi = 0;
        startCol = 0;
        endCol = 0;    
    }

    // Criando e preenchendo o arrayList:
    ArrayList<Integer> quad = new ArrayList<>();

    for(int i = startLi; i < endLi; i++){
      for(int j = startCol; j < endCol; j++){
        quad.add(matriz.get(i).get(j));
      }
    }

    return quad;
  }

  public String toString(){
    String str = "";
    for(int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        str += this.get(i, j) + " ";
      }
      str+= "\n"; // Pula linha
    }
    return str;
  }

  // Métodos utilizados no exercício 3:
  public int menorValor(){
    int menor = Integer.MAX_VALUE;
    for(int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        if(this.get(i, j) < menor){
          menor = this.get(i, j);
        }
      }
    }
    return menor;
  }

  public int maiorValor(){
    int maior = Integer.MIN_VALUE;
    for(int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        if(this.get(i, j) > maior){
          maior = this.get(i, j);
        }
      }
    }
    return maior;
  }
}