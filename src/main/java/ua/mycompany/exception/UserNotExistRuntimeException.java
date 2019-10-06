package ua.mycompany.exception;

public class UserNotExistRuntimeException extends RuntimeException{
    public UserNotExistRuntimeException(String message) {
        super(message);
    }
}
