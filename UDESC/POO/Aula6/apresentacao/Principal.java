package apresentacao;
import java.util.Scanner;

import dados.Fabrica;

public class Principal {

  public static void main(String[] args) {
    
    int fim = -1;
    Fabrica fabrica = new Fabrica();
    Scanner scanner = new Scanner(System.in);

    do{
      System.out.println(fabrica.fabricar().info());
      System.out.println("Deseja continuar? 1 - Sim / 0 - Não");
      fim = scanner.nextInt();
    }while(fim != 0);
    scanner.close();
  }

}
