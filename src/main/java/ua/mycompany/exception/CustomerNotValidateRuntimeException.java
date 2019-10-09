package ua.mycompany.exception;

public class CustomerNotValidateRuntimeException extends RuntimeException {
    public CustomerNotValidateRuntimeException(String message) {
        super(message);
    }
}
