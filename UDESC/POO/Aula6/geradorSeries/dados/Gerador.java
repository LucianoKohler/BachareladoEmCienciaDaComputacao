package dados;
import java.util.ArrayList;

public abstract class Gerador {
  protected ArrayList<Integer> sequencia;

  // Construtor
  public Gerador() {
    this.sequencia = new ArrayList<>();
  }

  public abstract void gerar(int qtd);

  public ArrayList<Integer> getSequencia() {
    return sequencia;
  }
}