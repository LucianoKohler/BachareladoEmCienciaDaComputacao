package apresentacao;

import java.util.Scanner;

import dados.*;
import exceptions.EspacoIndisponivelException;
import negocio.*;

public class Principal {
  private static SistemaZoologico s = new SistemaZoologico();
  private static Scanner scan = new Scanner(System.in);

  public static void imprimeMenu(){
    System.out.println("\n1 - Cadastrar Viveiro");
    System.out.println("2 - Listar Viveiros");
    System.out.println("3 - Cadastrar Animal");
    System.out.println("4 - Alocar Animal");
    System.out.println("5 - Mostrar animais em um viveiro");
    System.out.println("0 - Sair do Sistema");
    System.out.print("Sua escolha: ");
  }

  public static void cadastrarViveiro(){
    scan.nextLine();
    System.out.print("Insira o nome do viveiro: ");
    String nome = scan.nextLine();

    System.out.print("Insira o comprimento do viveiro: ");
    float comprimento = scan.nextFloat();

    System.out.print("Insira a largura do viveiro: ");
    float largura = scan.nextFloat();

    System.out.print("O viveiro é um aquário? (S/N): ");
    char r = scan.next().charAt(0);

    if(r == 'S' || r == 's'){
      System.out.print("Insira a altura do viveiro: ");
      float altura = scan.nextFloat();

      System.out.print("Insira a temperatura do viveiro: ");
      float temperatura = scan.nextFloat();

      Viveiro v = new Aquario(nome, comprimento, largura, altura, temperatura);
      s.cadastrarViveiro(v);
    }else{
      Viveiro v = new Viveiro(nome, comprimento, largura);
      s.cadastrarViveiro(v);
    }
  }

  public static void listarViveiros(){
    System.out.println("\nViveiros cadastrados: ");
    Viveiro[] viveiros = s.getViveiros();
    for(int i = 0; i < s.getQtdViveiros(); i++){
      System.out.println("Viveiro " + (i+1) + ": \n" + viveiros[i].toString());
    }
  }

  public static void listarAnimais(){
    System.out.println("\nAnimais cadastrados: ");
    Animal[] animais = s.getAnimais();
    for(int i = 0; i < s.getQtdAnimais(); i++){
      System.out.println("Animal " + (i+1) + ": \n" + animais[i].toString());
    }
  }

  public static void cadastrarAnimal(){
    System.out.print("O animal a cadastrar é um peixe? (S/N): ");
    char r = scan.next().charAt(0);

    System.out.print("Insira o nome do animal: ");
    scan.nextLine();
    String nome = scan.nextLine();

    System.out.print("Insira a cor do animal: ");
    String cor = scan.nextLine();

    System.out.print("Insira a espécie do animal: ");
    String especie = scan.nextLine();

    System.out.print("Insira a idade do animal: ");
    int idade = scan.nextInt();

    System.out.print("Insira a largura do animal: ");
    float largura = scan.nextFloat();

    System.out.print("Insira o comprimento do animal: ");
    float comprimento = scan.nextFloat();

    System.out.print("Insira a altura do animal: ");
    float altura = scan.nextFloat();

    if(r == 'S' || r == 's'){
      System.out.print("Insira a temperatura ideal do animal: ");
      float temperaturaIdeal = scan.nextFloat();

      Animal a = new Peixe(nome, cor, especie, idade, largura, comprimento, altura, temperaturaIdeal);
      s.cadastrarAnimal(a);
    }else{
      Animal a = new Animal(nome, cor, especie, idade, largura, comprimento, altura);
      s.cadastrarAnimal(a);
    }
  }

  public static Viveiro escolherViveiro(){
    System.out.println("Escolha o viveiro: ");
    listarViveiros();
    System.out.print("Sua escolha: ");
    int i = scan.nextInt();

    return s.getViveiros()[i-1];
  }

  public static Animal escolherAnimal(){
    System.out.println("Escolha o animal: ");
    listarAnimais();
    System.out.print("Sua escolha: ");
    int i = scan.nextInt();

    return s.getAnimais()[i-1];
  }

  public static void alocarAnimal() {
    Animal animal = escolherAnimal();
    Viveiro viveiro = escolherViveiro();

    try{
      s.alocarAnimal(viveiro, animal);
    } catch (EspacoIndisponivelException e){
      System.out.println(e.getMessage());
    }
  }

  public static void mostrarAnimaisEmUmViveiro(){
    System.out.println("Escolha o viveiro: ");
    listarViveiros();
    System.out.print("Sua escolha: ");
    int i = scan.nextInt();

    Viveiro viveiro = s.getViveiros()[i-1];
    System.out.println("Animais no viveiro " + i + ": " + viveiro.mostrarAnimaisNoViveiro());
  }

  public static void main(String[] args) {
    int opcao = -1;

    while(opcao != 0){
      imprimeMenu();
      opcao = scan.nextInt();

      switch (opcao) {
        case 0:
          System.out.println("Saindo do sistema...");
          break;
        case 1:
          cadastrarViveiro();
          break;

        case 2:
          listarViveiros();
          break;

        case 3:
          cadastrarAnimal();
          break;

        case 4:
          alocarAnimal();
          break;

        case 5:
          mostrarAnimaisEmUmViveiro();
          break;

        default:
          System.out.println("Opção inválida.");
      }
    }
    scan.close();
  }
}
