package negocio;

import dados.*;
import java.util.ArrayList;

public class Biblioteca {
  private ArrayList<Item> itens;
  private ArrayList<Usuario> usuarios;
  private ArrayList<Emprestimo> emprestimos;

  public Biblioteca() {
    this.itens = new ArrayList<Item>();
    this.usuarios = new ArrayList<Usuario>();
    this.emprestimos = new ArrayList<Emprestimo>();
  }

  public void cadastrarUsuario(Usuario usuario) {
    this.usuarios.add(usuario);
  }

  public void cadastrarItem(Item item) {
    this.itens.add(item);
  }

  public ArrayList<Emprestimo> getEmprestimosItem(Item item) {
    ArrayList<Emprestimo> emprestimosItem = new ArrayList<Emprestimo>();
    for (Emprestimo emprestimo : this.emprestimos) {
      if (emprestimo.getItem().equals(item)) {
        emprestimosItem.add(emprestimo);
      }
    }

    return emprestimosItem;
  }

  public ArrayList<Emprestimo> getEmprestimosUsuario(Usuario usuario) {
    ArrayList<Emprestimo> emprestimosUsuario = new ArrayList<Emprestimo>();
    for (Emprestimo emprestimo : this.emprestimos) {
      if (emprestimo.getUsuario().equals(usuario)) {
        emprestimosUsuario.add(emprestimo);
      }
    }

    return emprestimosUsuario;
  }

  public ArrayList<Item> getItens() {
    return itens;
  }

  public ArrayList<Usuario> getUsuarios() {
    return usuarios;
  }

  public boolean emprestar(Item item, Usuario usuario) {
    // Casos de falha
    if(item.estaEmprestado()) {
      System.out.println("Item já emprestado.");
      return false;
    }

    if(item instanceof Livro) {
      if (!usuario.podeEmpLivro()) {
        System.out.println("Limite de empréstimos atingido.");
        return false;
      }
    } else if (item instanceof Monografia) {
      if (!usuario.podeEmpMono()) {
        System.out.println("Limite de empréstimos atingido.");
        return false;
      }
    }

    // Caso de sucesso
    Emprestimo emprestimo = new Emprestimo();
    emprestimo.emprestar(item, usuario);
    this.emprestimos.add(emprestimo);
    return true;
  }

  public void devolver(Emprestimo emprestimo) {
    emprestimo.devolver();
  }
}
