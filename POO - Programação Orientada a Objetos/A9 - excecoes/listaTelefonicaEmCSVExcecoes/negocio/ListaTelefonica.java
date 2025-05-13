package negocio;

import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoCadastradoException;
import exceptions.ErroNaLeituraException;
import exceptions.ErroNaGravacaoException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import persistencia.ContatoDAO;

public class ListaTelefonica {
  private ContatoDAO contatoDAO = new ContatoDAO();
  private Map<Character, List<Contato>> contatos; // Para ajudar no código, depois será removido

  public ListaTelefonica() {
    contatos = new HashMap<Character,List<Contato>>();
  }

  public Map<Character, List<Contato>> listarContatos() throws ErroNaLeituraException {
    contatos = new HashMap<Character,List<Contato>>();
    for(char c = 'A'; c<= 'Z'; c++){
      List<Contato> contatosArquivo = contatoDAO.getAll();
      List<Contato> contatosDeMesmaLetra = new ArrayList<Contato>();
      for(Contato contato : contatosArquivo){
        if(contato.getNome().charAt(0) == c){
          contatosDeMesmaLetra.add(contato);
        }
      }
      contatos.put(c, contatosDeMesmaLetra);
    }
    return contatos;
  }

  public void adicionarContato(Contato contato) throws ContatoJaCadastradoException, ErroNaLeituraException, ErroNaGravacaoException {
    contatoDAO.insert(contato);
    System.out.println("Contato adicionado com sucesso.");
  }

  public void removerContato(String nome) throws ErroNaLeituraException, ErroNaGravacaoException, ContatoNaoCadastradoException {
    List<Contato> lista = contatoDAO.getAll();
    Contato contatoARemover = null;
    for (Contato contato : lista) {
      if (contato.getNome().equals(nome)) {
        contatoARemover = contato;
        break;
      }
    }
    if (contatoARemover != null) {
      contatoDAO.delete(nome);
      System.out.println("Contato removido com sucesso.");
    } else {
      System.out.println("Contato não encontrado.");
    }
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
