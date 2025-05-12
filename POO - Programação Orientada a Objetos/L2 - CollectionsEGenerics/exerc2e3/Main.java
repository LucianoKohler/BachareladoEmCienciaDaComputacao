import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    int li = 5; // Mude-me se quiser
    int col = 5; // Mude-me se quiser
    Matriz m = new Matriz(li, col);

    // Preenchendo a matriz
    for(int i = 0; i < li; i++){
      for(int j = 0; j < col; j++){
        System.out.print("Insira o valor do " + (i*col+j+1) + "° Valor: ");
        int num = scan.nextInt();
        m.set(num, i, j);
      }
    }

    // Mostrando a matriz
    // INPUT REALIZADO PELO PROFESSOR: 50 43 19 19 41 11 24 72 23 63 26 92 84 49 64 46 52 73 53 91 64 11 93 62 15
    System.out.println("Matriz: ");
    System.out.println(m.toString());
    scan.close();

    // Mostrando os quadrantes da matriz
    System.out.println("Valores no primeiro quadrante:");
    ArrayList<Object> primQuad = m.getElementosQuadrante(Quadrante.PRIMEIRO);
    primQuad.forEach((num) -> {System.out.print(num + " ");});

    System.out.println("\nValores no segundo quadrante:");
    ArrayList<Object> segunQuad = m.getElementosQuadrante(Quadrante.SEGUNDO);
    segunQuad.forEach((num) -> {System.out.print(num + " ");});
    
    System.out.println("\nValores no terceiro quadrante:");
    ArrayList<Object> tercQuad = m.getElementosQuadrante(Quadrante.TERCEIRO);
    tercQuad.forEach((num) -> {System.out.print(num + " ");});
    
    System.out.println("\nValores no quarto quadrante:");
    ArrayList<Object> quarQuad = m.getElementosQuadrante(Quadrante.QUARTO);
    quarQuad.forEach((num) -> {System.out.print(num + " ");});

    // Mostrando o menor e maior valor da matriz:
    System.out.println("Menor valor da matriz: " + m.menorValor());
    System.out.println("Maior valor da matriz: " + m.maiorValor());
  }  
}
