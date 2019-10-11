package ua.mycompany.exception;

public class CustomerNotExistRuntimeException extends AbstractLoggerRuntimeException{
    public CustomerNotExistRuntimeException(String message) {
        super(message);
    }
}
