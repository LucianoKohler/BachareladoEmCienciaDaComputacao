package persistencia;

import java.util.LinkedList;
import java.util.List;

import dados.Contato;

public class ArquivoContatoDAO {
  private final String caminho = "files/contatos.csv";
  private static EditorTexto arquivo = new EditorTexto();

  private String toCSV(Contato contato) {
    String p = "";
    p += contato.getNome() + ",";
    p += contato.getTelefone() + "\n";

    return p;
  }

  private Contato fromCSV(String linhaCSV) {
    String[] atributos = linhaCSV.split(",");

    Contato c = new Contato();
    c.setNome(atributos[0]);
    c.setTelefone(atributos[1]);

    return c;
  }

  private List<String> listaContatoToString(List<Contato> contatos) {
    List<String> arquivo = new LinkedList<String>();
    for (Contato c : contatos) {
      arquivo.add(toCSV(c));
    }
    return arquivo;
  }

  private List<Contato> stringToListaContato(List<String> arquivo) {
    List<Contato> contatos = new LinkedList<Contato>();
    for (String linha : arquivo) {
      contatos.add(fromCSV(linha));
    }
    return contatos;
  }

  public List<Contato> leContatosArquivo() {
    return stringToListaContato(arquivo.leTexto(caminho));
  }

  public void gravaContatosArquivo(List<Contato> contatos) {
    arquivo.gravaTexto(caminho, listaContatoToString(contatos));
  }

  public void salvaContatoArquivo(Contato contato) {
    arquivo.gravaTexto(caminho, toCSV(contato));
  }
}
