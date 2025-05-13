package exceptions;

public class ContatoJaCadastradoException extends Exception {
  public ContatoJaCadastradoException(){
    super("Contato já cadastrado!");
  }
}
