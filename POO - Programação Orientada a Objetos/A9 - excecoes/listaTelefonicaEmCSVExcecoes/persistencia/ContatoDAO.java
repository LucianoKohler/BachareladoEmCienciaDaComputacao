package persistencia;

import java.util.List;
import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoCadastradoException;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;

public class ContatoDAO {
  private ArquivoContatoDAO arquivoContatoDAO = new ArquivoContatoDAO();

  public void insert(Contato contato) throws ContatoJaCadastradoException, ErroNaLeituraException {
    List<Contato> contatos = arquivoContatoDAO.leContatosArquivo();
    for(Contato c : contatos){
      if(c.getNome().equals(contato.getNome())){
        throw new ContatoJaCadastradoException();
      }
    }
      arquivoContatoDAO.salvaContatoArquivo(contato);
  }

  public void delete(String nome) throws ContatoNaoCadastradoException, ErroNaLeituraException, ErroNaGravacaoException { // Obs: A função normalmente receberia um objeto, mas usar o nome é mais simples nesse projeto.
    List<Contato> contatos = arquivoContatoDAO.leContatosArquivo();
    for(Contato contato : contatos) {
      if (contato.getNome().equals(nome)) {
        contatos.remove(contato);
        arquivoContatoDAO.gravaContatosArquivo(contatos);
        return;
      }
    }
    // Se não encontrou
    throw new ContatoNaoCadastradoException();
  }

  public List<Contato> getAll() throws ErroNaLeituraException {
    return arquivoContatoDAO.leContatosArquivo();
  }
}
