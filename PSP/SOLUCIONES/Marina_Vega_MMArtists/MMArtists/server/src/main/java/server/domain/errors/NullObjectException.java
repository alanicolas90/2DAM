package server.domain.errors;

public class NullObjectException extends RuntimeException {
    public NullObjectException(String message) {
        super(message);
    }
}