import animais.*;

public class Main {
    public static void main(String[] args) {
        // Criando objetos de cada classe
        Animal gato1 = new Gato("Felix");
        Animal gato2 = new Gato("Garfield");
        Animal vaca1 = new Vaca("Mimosa");
        Animal vaca2 = new Vaca("Bela");
        Animal esquilo1 = new Esquilo("Tico");
        Animal esquilo2 = new Esquilo("Teco");

        // Exibindo informações dos animais
        System.out.println(gato1.emitirSom());
        System.out.println(gato2.emitirSom());
        System.out.println(vaca1.emitirSom());
        System.out.println(vaca2.emitirSom());
        System.out.println(esquilo1.emitirSom());
        System.out.println(esquilo2.emitirSom());
    }
  
}
