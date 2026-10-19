package exceptions;

public class MedicoNaoDisponivelException extends RuntimeException {
  public MedicoNaoDisponivelException(String message) {
    super(message);
  }
}