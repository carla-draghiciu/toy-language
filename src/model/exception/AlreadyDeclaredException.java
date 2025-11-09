package model.exception;

public class AlreadyDeclaredException extends RuntimeException {
    public AlreadyDeclaredException(String message) {
        super(message);
    }
}
