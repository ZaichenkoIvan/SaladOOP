package ua.mycompany.exception;

public class UncorrectedLoginRuntimeException extends RuntimeException {
    public UncorrectedLoginRuntimeException(String message) {
        super(message);
    }
}
