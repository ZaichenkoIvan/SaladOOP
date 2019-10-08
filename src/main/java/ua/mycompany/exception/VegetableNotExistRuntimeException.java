package ua.mycompany.exception;

public class VegetableNotExistRuntimeException extends RuntimeException{
    public VegetableNotExistRuntimeException(String message) {
        super(message);
    }
}
