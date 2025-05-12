package apresentacao;
import formas.*;

public class Main {
  public static void main(String[] args) {
  
    // Criando objetos de cada classe
    Circulo c1 = new Circulo(5);
    Circulo c2 = new Circulo(10);
    TrianguloEquilatero t1 = new TrianguloEquilatero(3);
    TrianguloEquilatero t2 = new TrianguloEquilatero(6);
    Losango l1 = new Losango(4, 6);
    Losango l2 = new Losango(8, 10);

    // Exibindo informações das formas
    System.out.println(c1.toString());
    System.out.println(c2.toString());
    System.out.println(t1.toString());
    System.out.println(t2.toString());
    System.out.println(l1.toString());
    System.out.println(l2.toString());
  }
}
