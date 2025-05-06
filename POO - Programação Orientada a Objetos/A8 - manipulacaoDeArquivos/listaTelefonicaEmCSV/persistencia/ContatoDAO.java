package persistencia;

import java.util.List;
import dados.Contato;

public class ContatoDAO {
  private ArquivoContatoDAO arquivoContatoDAO = new ArquivoContatoDAO();

  public void insert(Contato contato) {
    arquivoContatoDAO.salvaContatoArquivo(contato);
  }

  public void delete(String nome) { // Obs: A função normalmente receberia um objeto, mas usar o nome é mais simples nesse projeto.
    List<Contato> contatos = arquivoContatoDAO.leContatosArquivo();
    for(Contato contato : contatos) {
      if (contato.getNome().equals(nome)) {
        contatos.remove(contato);
        arquivoContatoDAO.gravaContatosArquivo(contatos);
        break;
      }
    }
  }

  public List<Contato> getAll() {
    return arquivoContatoDAO.leContatosArquivo();
  }
}
