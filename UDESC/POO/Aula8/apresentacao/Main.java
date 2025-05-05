package apresentacao;

import java.util.Scanner;

import dados.Pessoa;
import negocio.Sistema;

public class Main {

  private static Sistema sistema = new Sistema();
  private static Scanner s = new Scanner(System.in);

  private static Pessoa novaPessoa() {
    Pessoa p = new Pessoa();
    System.out.print("Digite o nome: ");
    p.setNome(s.nextLine());

    System.out.print("Digite a idade: ");
    p.setIdade(Integer.parseInt(s.nextLine()));

    System.out.print("Digite a altura: ");
    p.setAltura(Float.parseFloat(s.nextLine()));

    System.out.print("Digite o peso: ");
    p.setPeso(Float.parseFloat(s.nextLine()));

    return p;
  }

  private static void mostrarPessoas() {
    if(sistema.getLista().isEmpty()) {
      System.out.println("Lista vazia");
      return;
    }

    // Se houver cadastros
    for (int i = 0; i < sistema.getLista().size(); i++) {
      System.out.println("Pessoa " + (i + 1) + ": ");
      System.out.println(sistema.getLista().get(i));
    }
  }

  private static int escolherPessoaPorID() {
    mostrarPessoas();
    System.out.print("Escolha uma pessoa: ");
    int escolha = Integer.parseInt(s.nextLine()) - 1;
    if (escolha < sistema.getLista().size() && escolha >= 0) {
      return escolha;
    } else {
      System.out.println("Escolha inválida!");
      return -1;
    }
  }

  public static void imprimirMenu() {
    System.out.println("--------------------------------------------");
    System.out.println("Selecione uma operação: ");
    System.out.println("0 - Sair");
    System.out.println("1 - Cadastrar pessoa");
    System.out.println("2 - Remover pessoa");
    System.out.println("3 - Mostrar pessoas");
    System.out.print("Sua escolha: ");
  }

  public static void main(String[] args) {
    int opcao = -1;
    while (opcao != 0) {
      imprimirMenu();
      opcao = Integer.parseInt(s.nextLine());
      System.out.println("--------------------------------------------");
      switch (opcao) {
        case 0:
          System.out.println("Saindo do programa");
          break;
        case 1:
          sistema.adicionarPessoa(novaPessoa());
          break;
        case 2:
          sistema.removerPessoa(escolherPessoaPorID());
          break;
        case 3:
          mostrarPessoas();
          break;
        default:
          System.out.println("Valor inválido!");
      }
    }
  }
}
