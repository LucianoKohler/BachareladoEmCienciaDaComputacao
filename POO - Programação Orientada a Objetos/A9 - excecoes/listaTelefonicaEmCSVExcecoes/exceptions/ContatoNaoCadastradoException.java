package exceptions;

public class ContatoNaoCadastradoException extends Exception {
  public ContatoNaoCadastradoException(){
    super("Contato não cadastrado!");
  }
}
