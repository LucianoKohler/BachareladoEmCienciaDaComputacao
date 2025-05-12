package grafos;

import java.util.ArrayList;
import java.util.Collections;

public class Digrafo {
  private ArrayList<ArrayList<Integer>> matrizAdjacencia = new ArrayList<>();

  public Digrafo(int tam) {
    for(int i = 0; i < tam+1; i++){
      // Inicializa a matriz como tudo 0
      matrizAdjacencia.add(new ArrayList<Integer>(Collections.nCopies(tam, 0)));
    }
  }

  public void adicionarVertice() {
    matrizAdjacencia.add(new ArrayList<>());
    for (int i = 0; i < matrizAdjacencia.size() - 1; i++) {
      // Adiciona 0 para a nova linha
      matrizAdjacencia.get(matrizAdjacencia.size() - 1).add(0);
      // Adiciona 0 para a nova coluna
      matrizAdjacencia.get(i).add(0);
    }
  }

  public void adicionarAresta(int origem, int destino) {
    matrizAdjacencia.get(origem).set(destino, 1);
  }

  public String toString() {
    String s = "  ";
    for(int i = 0; i < matrizAdjacencia.size()-1; i++) {
      s += i + " ";
    }
    s+= "\n";

    for(int i = 0; i < matrizAdjacencia.size()-1; i++) {
      s+= i + " ";
      for(int j = 0; j < matrizAdjacencia.size()-1; j++){
        s+= matrizAdjacencia.get(i).get(j) + " ";
      }
      s+= "\n";
    }
    return s;
  } 
}
