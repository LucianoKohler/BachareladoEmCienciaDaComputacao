import java.util.ArrayList;

public class Matriz<T> {
  private int n; // N° de linhas
  private int m; // N° de colunas
  private ArrayList<ArrayList<Object>> matriz;

  // Construtor
  public Matriz(int n, int m) {
    this.n = n;
    this.m = m;
    matriz = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      ArrayList<Object> linha = new ArrayList<>();
      for (int j = 0; j < m; j++) {
        linha.add(0);
      }
      matriz.add(linha);
    }
  }

  public Object get(int li, int col){
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
  
  public ArrayList<Object> getLinha(int li){
    if(li > n || li < 0){
      System.out.println("Índices fora dos limites");
      return null;
    }else{
      return matriz.get(li-1); // Contando de 0 a 4, logo a 1° linha é a 0°
    }
  }

  public ArrayList<Object> getColuna(int col){
    if(col > m || col < 0){
      System.out.println("Índices fora dos limites");
      return null;
    }else{
      ArrayList<Object> coluna = new ArrayList<>();
      for(int i = 0; i < n; i++){
        coluna.add(matriz.get(i).get(col-1)); // Contando de 0 a 4, logo a 1° coluna é a 0°
      }
      return coluna;
    }
  }

  public ArrayList<Object> getElementosQuadrante(Quadrante quadrante){
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
    ArrayList<Object> quad = new ArrayList<>();

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
  public Object menorValor(){
    Double menor = Double.MAX_VALUE;
    for(int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        Double valor = (Double) this.get(i, j);
        if(valor < menor){
          menor = valor;
        }
      }
    }
    return menor;
  }

  public Object maiorValor(){
    Double maior = Double.MIN_VALUE;
    for(int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        Double valor = (Double) this.get(i, j);
        if(valor > maior){
          maior = valor;
        }
      }
    }
    return maior;
  }
}