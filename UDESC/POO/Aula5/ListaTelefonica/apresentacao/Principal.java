package apresentacao;
import dados.Contato;
import negocio.ListaTelefonica;

public class Principal {
  private ListaTelefonica lista = new ListaTelefonica();

  public ListaTelefonica getLista() {
    return lista;
  }

  public void setLista(ListaTelefonica lista) {
    this.lista = lista;
  }

  public static void main(String[] args) {
    Principal p = new Principal();

    Contato c1 = new Contato("Niels Bohr", "(47)98822-0909");
    Contato c2 = new Contato("John Lenon", "(47)98821-0902");

    p.lista.adicionarContato(c1);
    p.lista.adicionarContato(c2);
    System.out.println(p.getLista());
    p.lista.removerContato(c1);
    System.out.println(p.getLista());
  }
}
