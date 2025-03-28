import java.util.Scanner;

public class NomesEIdades {
  public static void main (String[] args) {

    Scanner input = new Scanner(System.in);
    int[] idades = new int[3];
    String[] nomes = new String[3];

    // Entrada
    for (int i = 0; i < 3; i++) {
      System.out.println("Insira o nome da " + (i+1) + "° pessoa e sua idade em linhas separadas: ");
      nomes[i] = input.nextLine();
      idades[i] = Integer.parseInt(input.nextLine());
    }

    // Saída
    System.out.println("Eis os nomes e idades das pessoas digitadas: ");
    for(int i = 0; i < 3; i++){
      System.out.println(nomes[i] + ", Idade: " + idades[i]);
    }

    input.close();
  }

}