

import dados.*;
import negocio.Sistema;
import java.util.Scanner;

public class Principal {
  private static Sistema sistema = new Sistema();
  private static Scanner scanner = new Scanner(System.in);

  private static ContaCorrente novaContaCorrente() {
    ContaCorrente conta = new ContaCorrente();
    System.out.print("Digite o CPF: ");
    conta.setCpf(scanner.nextLong());
    return conta;
  }

  private static ContaSalario novaContaSalario() {
    ContaSalario conta = new ContaSalario();
    System.out.print("Digite o CPF: ");
    conta.setCpf(scanner.nextLong());
    System.out.print("Digite o CNPJ da empresa: ");
    conta.setCnpjEmpresa(scanner.nextLong());
    return conta;
  }

  private static void cadastrarConta(){
    System.out.println("Esolha o tipo de conta para cadastrar: ");
    System.out.println("1 - Conta Corrente");
    System.out.println("2 - Conta Salário");
    System.out.print("Sua escolha: ");

    int escolha = scanner.nextInt();

    switch (escolha) {
      case 1:
        sistema.cadastrarConta(novaContaCorrente());
        break;
      
      case 2:
        sistema.cadastrarConta(novaContaSalario());
        break;

      default:
        System.out.println("Escolha inválida");  
        break;
    }
  }

  private static void exibirContas(){
    for(int i = 0; i < sistema.getQuantContas(); i++){
      System.out.println("Conta N° " + (i+1) + ": " + sistema.getContasBancarias()[i].toString() + "\n");
    }
  }

  // Método usado para retornar uma conta em específico e usar em outros métodos
  private static ContaBancaria escolherConta(){
    exibirContas();
    System.out.print("Escolha a conta para fazer a operação: ");
    int escolha = scanner.nextInt()-1;

    if(escolha < sistema.getQuantContas()){
      return sistema.getContasBancarias()[escolha];
    }else{
      System.out.println("Escolha inválida.");
      return null;
    }
  }

  private static void realizarSaque(){
    ContaBancaria conta = escolherConta();

    System.out.print("Digite o valor a ser sacado: ");
    float valor = scanner.nextFloat();

    if(conta != null){
      sistema.realizarSaque(conta, valor);
    }else{
      System.out.println("Conta não encontrada.");
    }
  }

  private static void realizarDeposito(){
    ContaBancaria conta = escolherConta();

    System.out.print("Digite o valor a ser depositado: ");
    float valor = scanner.nextFloat();

    if(conta != null){
      if(conta instanceof ContaSalario){
        System.out.print("Insira o CNPJ da empresa: ");
        int cnpj = scanner.nextInt();
        if(sistema.realizarDeposito((ContaSalario) conta, valor, cnpj)){
          System.out.println("Depósito feito, novo saldo da conta: R$" + conta.getSaldo());
        }
      }else{
        sistema.realizarDeposito((ContaCorrente) conta, valor);
        System.out.println("Depósito feito, novo saldo da conta: R$" + conta.getSaldo());
      }
    }else{
      System.out.println("Conta não encontrada.");
    }
  }

  private static void mostrarExtrato() {
    ContaBancaria conta = escolherConta();
    if(conta != null){
      System.out.println(conta.gerarExtrato());
    }
  }

  private static void imprimeMenu() {
  System.out.println("\nEscolha uma opção:");
  System.out.println("0 - Sair");
  System.out.println("1 - Cadastrar Conta");
  System.out.println("2 - Realizar Saque");
  System.out.println("3 - Realizar Depósito");
  System.out.println("4 - Exibir Extrato");  
  System.out.print("Sua escolha: ");
  }

  public static void main(String[] args){
    int opcao = -1;

    while(opcao != 0){
      imprimeMenu();
      opcao = scanner.nextInt();

      switch (opcao) {
        case 0:
          System.out.println("Saindo do sistema...");
          break;
        case 1:
          cadastrarConta();
          break;

        case 2:
          realizarSaque();
          break;

        case 3:
          realizarDeposito();
          break;

        case 4:
          mostrarExtrato();
          break;

        default:
          System.out.println("Opção inválida.");
      }
    }
  }
}
