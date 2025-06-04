package exceptions;

public class NomeInvalidoException extends Exception {
  public NomeInvalidoException(String message) {
    System.out.println("Erro: " + message);
  }
}