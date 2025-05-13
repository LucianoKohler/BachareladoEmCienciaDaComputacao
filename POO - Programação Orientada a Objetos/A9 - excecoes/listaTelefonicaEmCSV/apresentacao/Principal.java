package apresentacao;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoCadastradoException;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;
import negocio.ListaTelefonica;

public class Principal {
  private Scanner scanner = new Scanner(System.in);
  private ListaTelefonica lista = new ListaTelefonica();

  public void adicionarContato(){
    System.out.print("Insira o nome do indivíduo: ");
    String nome = scanner.nextLine();

    System.out.print("Insira o telefone do indivíduo: ");
    String telefone = scanner.nextLine();

    try {
      lista.adicionarContato(new Contato(nome, telefone));
        } catch (ErroNaLeituraException e){
          System.out.println(e.getMessage());
          System.out.println("Caminho informado: " + e.getCaminho());
        } catch (ErroNaGravacaoException e){
          System.out.println(e.getMessage());
          System.out.println("Caminho informado: " + e.getCaminho());
        } catch (ContatoJaCadastradoException e){
          System.out.println(e.getMessage());
        }
  }

  public void mostrarContatos() { // Em resumo, é usado getKey e getValue para pegar os valores do map.
    System.out.println("MOSTRANDO TODOS OS CONTATOS:");
    System.out.println("=========================================");
    try {
      Map<Character, List<Contato>> contatos = lista.listarContatos();
      for (Map.Entry<Character, List<Contato>> entry : contatos.entrySet()) {
        System.out.println(entry.getKey() + ":");
        for (Contato contato : entry.getValue()) {
          System.out.println("Nome: " + contato.getNome() + " - Tel: " + contato.getTelefone());
        }
      }
      System.out.println("=========================================");
    } catch (ErroNaLeituraException e){
      System.out.println(e.getMessage());
      System.out.println("Caminho informado: " + e.getCaminho());
    }
  }

  public String escolherContato(){
    mostrarContatos();
    System.out.print("Escolha o contato para fazer a operação (escreva o nome completo): ");
    String nome = scanner.nextLine();
    return nome;
  }

  public void removerContato(String nome) {
    try {
      lista.removerContato(nome);
    } catch (ErroNaLeituraException e){
      System.out.println(e.getMessage());
      System.out.println("Caminho informado: " + e.getCaminho());
    } catch (ErroNaGravacaoException e){
      System.out.println(e.getMessage());
      System.out.println("Caminho informado: " + e.getCaminho());
    } catch (ContatoNaoCadastradoException e){
      System.out.println(e.getMessage());
    }
  }

  public void setLista(ListaTelefonica lista) {
    this.lista = lista;
  }

  public void imprimeMenu() {
    System.out.println("=========================================");
    System.out.println("0 - Sair");
    System.out.println("1 - Adicionar contato");
    System.out.println("2 - Remover contato");
    System.out.println("3 - Listar contatos");
  }

  public static void main(String[] args) {
    Principal p = new Principal();
    int opcao = -1;
    while (opcao != 0) {
      p.imprimeMenu();
      System.out.print("Sua escolha: ");
      opcao = Integer.parseInt(p.scanner.nextLine());
      switch (opcao) {
        case 0:
          System.out.println("Saindo do programa");
          break;
        case 1:
          p.adicionarContato();
          break;
        case 2:
          String nome = p.escolherContato();
          p.removerContato(nome);
          break;
        case 3:
          p.mostrarContatos();
          break;
        default:
          System.out.println("Opção inválida.");
      }
    }
  }
}
