package persistencia;

import java.util.List;
import dados.Pessoa;

public class PessoaDAO {
  private ArquivoPessoaDAO arquivoPessoaDAO = new ArquivoPessoaDAO();

  public void insert(Pessoa pessoa) {
    arquivoPessoaDAO.salvaPessoaArquivo(pessoa);
  }

  public void delete(int IDPessoa) {
    List<Pessoa> pessoas = arquivoPessoaDAO.lePesoasArquivo();
    pessoas.remove(IDPessoa);
    arquivoPessoaDAO.gravaPesoasArquivo(pessoas);
  }

  public List<Pessoa> getAll() {
    return arquivoPessoaDAO.lePesoasArquivo();
  }
}
