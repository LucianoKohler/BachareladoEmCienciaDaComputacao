package apresentacao;

import grafos.*;

public class Main {

  public static void main(String[] args){
    
    // Criando um grafo
    Grafo g = new Grafo(5);

    // Adicionando arestas
    g.adicionarAresta(0, 1);
    g.adicionarAresta(0, 2);
    g.adicionarAresta(1, 1);
    g.adicionarAresta(1, 2);
    g.adicionarAresta(1, 3);
    g.adicionarAresta(1, 4);
    g.adicionarAresta(2, 3);
    g.adicionarAresta(3, 4);

    // Criando um dígrafo
    Digrafo d = new Digrafo(5);
    
    // Adicionando arestas
    d.adicionarAresta(0, 1);
    d.adicionarAresta(0, 2);
    d.adicionarAresta(1, 1);
    d.adicionarAresta(1, 3);
    d.adicionarAresta(2, 0);
    d.adicionarAresta(2, 1);
    d.adicionarAresta(2, 3);
    d.adicionarAresta(3, 4);
    d.adicionarAresta(4, 1);

    System.out.println("Conexões do grafo:");
    System.out.println(g.toString());
    System.out.println("Conexões do dígrafo:");
    System.out.println(d.toString());

  }
}
