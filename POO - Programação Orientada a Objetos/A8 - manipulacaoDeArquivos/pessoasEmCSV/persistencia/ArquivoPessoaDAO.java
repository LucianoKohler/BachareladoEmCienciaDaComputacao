package persistencia;

import java.util.LinkedList;
import java.util.List;

import dados.Pessoa;

public class ArquivoPessoaDAO {
  private final String caminho = "files/pessoas.csv";
  private static EditorTexto arquivo = new EditorTexto();

  private String toCSV(Pessoa pessoa) {
    String p = "";
    p += pessoa.getNome() + ",";
    p += pessoa.getIdade() + ",";
    p += pessoa.getAltura() + ",";
    p += pessoa.getPeso() + "\n";

    return p;
  }

  private Pessoa fromCSV(String linhaCSV) {
    String[] atributos = linhaCSV.split(",");

    Pessoa p = new Pessoa();
    p.setNome(atributos[0]);
    p.setIdade(Integer.parseInt(atributos[1]));
    p.setAltura(Float.parseFloat(atributos[2]));
    p.setPeso(Float.parseFloat(atributos[3]));

    return p;
  }

  private List<String> listaPessoaToString(List<Pessoa> pessoas) {
    List<String> arquivo = new LinkedList<String>();
    for (Pessoa p : pessoas) {
      arquivo.add(toCSV(p));
    }
    return arquivo;
  }

  private List<Pessoa> stringToListaPessoa(List<String> arquivo) {
    List<Pessoa> pessoas = new LinkedList<Pessoa>();
    for (String linha : arquivo) {
      pessoas.add(fromCSV(linha));
    }
    return pessoas;
  }

  public List<Pessoa> lePesoasArquivo() {
    return stringToListaPessoa(arquivo.leTexto(caminho));
  }

  public void gravaPesoasArquivo(List<Pessoa> pessoas) {
    arquivo.gravaTexto(caminho, listaPessoaToString(pessoas));
  }

  public void salvaPessoaArquivo(Pessoa pessoa) {
    arquivo.gravaTexto(caminho, toCSV(pessoa));
  }
}
