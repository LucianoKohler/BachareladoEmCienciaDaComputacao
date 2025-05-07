package apresentacao;

import java.util.Scanner;
import negocio.Biblioteca;
import dados.*;


public class Principal {
  static Principal p = new Principal();
  Scanner s = new Scanner(System.in);
  Biblioteca biblioteca = new Biblioteca();

  public void cadastrarUsuario() {
    System.out.println("Digite o login do usuário:");
    String login = s.nextLine();
    System.out.println("Digite a senha do usuário:");
    String senha = s.nextLine();
    System.out.println("Digite o limite de empréstimos de livros:");
    int limiteEmpLivro = s.nextInt();
    System.out.println("Digite o limite de empréstimos de monografias:");
    int limiteEmpMono = s.nextInt();
    
    biblioteca.cadastrarUsuario(new Usuario(login, senha, limiteEmpLivro, limiteEmpMono));
    System.out.println("Usuário cadastrado com sucesso.");
  }

  public void cadastrarItem() {
    System.out.println("Digite o título do item:");
    String titulo = s.nextLine();
    System.out.println("Digite o nome do autor:");
    String nomeAutor = s.nextLine();
    System.out.println("Digite o ano de publicação:");
    int ano = s.nextInt();
    
    System.out.println("Digite o tipo do item (1 - Livro, 2 - Monografia):");
    int tipo = s.nextInt();
    
    if(tipo == 1) {
      biblioteca.cadastrarItem(new Livro(biblioteca.getItens().size()+1, titulo, nomeAutor, ano));
      System.out.println("Livro cadastrado com sucesso.");
    } else if(tipo == 2) {
      biblioteca.cadastrarItem(new Monografia(biblioteca.getItens().size()+1, titulo, nomeAutor, ano));
      System.out.println("Monografia cadastrada com sucesso.");
    } else {
      System.out.println("Tipo inválido.");
    }
  }

  public void mostraTodosItens() {
    System.out.println("Itens cadastrados:");
    for (Item item : biblioteca.getItens()) {
      System.out.println(item.toString());
    }
  }

  public void mostraTodosUsuarios() {
    System.out.println("Usuários cadastrados:");
    for (Usuario usuario : biblioteca.getUsuarios()) {
      int contador = 1;
      System.out.println("Usuario " + contador + ": " + usuario.toString());
    }
  }

  public int escolherItem() {
    mostraTodosItens();
    System.out.print("Escolha um item digitando seu ID: ");
    int idItem = s.nextInt();
    return idItem-1;
  }

  public int escolherUsuario() {
    mostraTodosUsuarios();
    System.out.print("Escolha um usuário digitando seu ID: ");
    int idUsuario = s.nextInt();
    
    return idUsuario-1;
  }

  public void mostrarEmprestimosItem() {
    // Já está bom para revisar...
  }

  public void mostrarEmprestimosUsuario() {
    // Já está bom para revisar...

  }

  public void emprestarItem() {
    // Já está bom para revisar...

  }

  public void devolverItem() {
    // Já está bom para revisar...
  }

  public static void imprimeMenu() {
    System.out.println("Escolha uma opção:");
    System.out.println("0 - Sair do programa");
    System.out.println("1 - Cadastrar Usuário");
    System.out.println("2 - Cadastrar Item");
    System.out.println("3 - Mostrar todos os Itens");
    System.out.println("4 - Mostrar todos os Usuários");
    // System.out.println("5 - Mostrar Emprestimos de um Item");
    // System.out.println("6 - Mostrar Emprestimos de um Usuário");
    // System.out.println("7 - Emprestar Item");
    // System.out.println("8 - Devolver Item");
  }
  
  public static void main(String[] args) {
    int escolha = -1;
    while(escolha != 0) {

      imprimeMenu();
      escolha = p.s.nextInt();
      p.s.nextLine(); // Limpa o buffer
      
      switch (escolha) {
        case 1:
          p.cadastrarUsuario();
        break;
        case 2:
          p.cadastrarItem();
        break;
        case 3:
          p.mostraTodosItens();
        break;
        case 4:
          p.mostraTodosUsuarios();
        break;
        // case 5:
        //   p.mostrarEmprestimosItem();
        // break;
        // case 6:
        //   p.mostrarEmprestimosUsuario();
        // break;
        // case 7:
        //   p.emprestarItem();
        // break;
        // case 8:
        //   p.devolverItem();
        // break;
        
        default:
        System.out.println("Opção inválida.");
        break;
      }
    }
  }
}
