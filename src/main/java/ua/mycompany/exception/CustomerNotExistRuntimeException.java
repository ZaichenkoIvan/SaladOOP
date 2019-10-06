package ua.mycompany.exception;

public class CustomerNotExistRuntimeException extends RuntimeException{
    public CustomerNotExistRuntimeException(String message) {
        super(message);
    }
}
