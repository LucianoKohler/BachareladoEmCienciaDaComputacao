package exceptions;

public class EspacoIndisponivelException extends Exception {
    public EspacoIndisponivelException() {
        super("Espaço indisponível no viveiro para alocar o animal.");
    }
}