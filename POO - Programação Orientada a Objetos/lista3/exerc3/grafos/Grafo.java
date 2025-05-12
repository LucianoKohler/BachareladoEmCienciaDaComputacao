package grafos;

public class Grafo extends Digrafo {
    public Grafo(int tam) {
        super(tam);
    }

    @Override
    public void adicionarAresta(int origem, int destino) {
        super.adicionarAresta(origem, destino);
        super.adicionarAresta(destino, origem);
    }
  
}
