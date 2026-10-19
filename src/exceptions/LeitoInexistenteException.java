package exceptions;
public class LeitoInexistenteException extends RuntimeException {
    public LeitoInexistenteException(String message) {
        super(message);
    }
}