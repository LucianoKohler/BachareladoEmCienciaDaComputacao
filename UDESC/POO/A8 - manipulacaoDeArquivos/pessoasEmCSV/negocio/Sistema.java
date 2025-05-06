package negocio;

import java.util.List;

import dados.Pessoa;
import persistencia.PessoaDAO;

public class Sistema {
    private PessoaDAO pessoaDAO = new PessoaDAO();

    public void adicionarPessoa(Pessoa pessoa) {
        pessoaDAO.insert(pessoa);
    }

    public void removerPessoa(int IDPessoa) {
        if(IDPessoa != -1){
          System.out.println("Pessoa removida com sucesso!");
          pessoaDAO.delete(IDPessoa);
        }
    }

    public List<Pessoa> getLista() {
        return pessoaDAO.getAll();
    }
}
