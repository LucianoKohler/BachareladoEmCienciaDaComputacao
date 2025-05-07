package dados;

public class Emprestimo {
  private int idEmprestimo;
  private boolean devolvido;
  Item item;
  Usuario usuario;

  public int getIdEmprestimo() {
    return idEmprestimo;
  }
  public void setIdEmprestimo(int idEmprestimo) {
    this.idEmprestimo = idEmprestimo;
  }

  public Item getItem() {
    return item;
  }
  public void setItem(Item item) {
    this.item = item;
  }

  public Usuario getUsuario() {
    return usuario;
  }
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public void emprestar(Item itemEmprestado, Usuario usuarioEmprestimo) {
    if (itemEmprestado.estaEmprestado()) {
      System.out.println("Item já emprestado.");
      return;
    }

    // Se o limite de empréstimo do item for atingido, retorna
    if(itemEmprestado instanceof Livro) {
      if (usuarioEmprestimo.getLimiteEmpLivro() <= 0) {
        System.out.println("Limite de empréstimos atingido.");
        return;
      }
    } else if (itemEmprestado instanceof Monografia) {
      if (usuarioEmprestimo.getLimiteEmpMono() <= 0) {
        System.out.println("Limite de empréstimos atingido.");
        return;
      }
    }
    
    // Faz o empréstimo
    this.item = itemEmprestado;
    this.usuario = usuarioEmprestimo;
    if(itemEmprestado instanceof Livro) {
      usuarioEmprestimo.setLimiteEmpLivro(usuarioEmprestimo.getLimiteEmpLivro() - 1);
    } else if (itemEmprestado instanceof Monografia) {
      usuarioEmprestimo.setLimiteEmpMono(usuarioEmprestimo.getLimiteEmpMono() - 1);
    }
    itemEmprestado.emprestar();
    this.devolvido = false;
  }

  public void devolver() {
    if (this.devolvido) {
      System.out.println("Item já devolvido.");
      return;
    }
    this.devolvido = true;
    this.item.devolver();
    if(item instanceof Livro) {
      usuario.setLimiteEmpLivro(usuario.getLimiteEmpLivro() + 1);
    } else if (item instanceof Monografia) {
      usuario.setLimiteEmpMono(usuario.getLimiteEmpMono() + 1);
    }
  }
}
