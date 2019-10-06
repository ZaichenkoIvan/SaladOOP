package ua.mycompany.exception;

public class UncorrectLoginException extends RuntimeException {
    public UncorrectLoginException(String message) {
        super(message);
    }
}
