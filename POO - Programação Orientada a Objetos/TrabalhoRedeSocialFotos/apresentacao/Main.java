package apresentacao;

import negocio.Sistema;

import java.util.Scanner;

import dados.*;

public class Main {
  public User userLogado = new User(0, null, null, null, null);
  public boolean modoDebugging = false;
  public Sistema s = new Sistema();
  public Scanner scanner = new Scanner(System.in);

  public void imprimeMenuUser(){
    System.out.println("Bem vindo, " + userLogado.getNomeCompleto() + "!");
    System.out.println("Escolhas...");
  }

  public static void imprimeMenuDebugger(){
    System.out.println("Menu de debugging acionado, ferramentas de debugging: ");
    System.out.println("Escolhas...");
  }

  public static void main(String[] args) {
    Main m = new Main();
    String escolha = "-1";

    /* -------------------------------------------- */
    /* String para entrar/sair do modo debug: debug */
    /* -------------------------------------------- */
    
    while(!escolha.equals("0")){

      if(escolha.equals("debug")){
        if(m.modoDebugging){
          m.modoDebugging = false;
        }else{
          m.modoDebugging = true;
        }
      }

      if(m.modoDebugging){
        imprimeMenuDebugger();
      }else{
        m.imprimeMenuUser();
      }
      System.out.print("Sua escolha: ");
      escolha = m.scanner.nextLine();
    }
  }
}
