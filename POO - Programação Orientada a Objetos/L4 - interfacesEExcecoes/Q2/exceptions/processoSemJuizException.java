package exceptions;

public class processoSemJuizException extends Exception {
    public processoSemJuizException() {
        super("Erro: O(s) processo(s) não possui(em) juíz");
    }
  
}
