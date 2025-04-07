package negocio;

import dados.Contato;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class ListaTelefonica {
  private Map<Character, List<Contato>> contatos;

  public ListaTelefonica() {
    contatos = new HashMap<Character,List<Contato>>();
    for(char c = 'A'; c<= 'Z'; c++){
      contatos.put(c, new LinkedList<Contato>());
    }
  }

  public void adicionarContato(Contato contato) {
    char inicial = contato.getNome().toUpperCase().charAt(0);
    List<Contato> lista = contatos.get(inicial);
    lista.add(contato);
  }

  public void removerContato(Contato contato) {
    char inicial = contato.getNome().toUpperCase().charAt(0);
    List<Contato> lista = contatos.get(inicial);
    lista.remove(contato);
  }

  public String toString() {
    StringBuffer str = new StringBuffer();
    contatos.forEach((chave, valores) -> {
      str.append(chave+":\n");
      valores.forEach((valor) -> {
        str.append(valor+":\n");
      });
    });
    return str.toString();
  }
}
