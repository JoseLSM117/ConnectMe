package dev.connectme.connectme.auth.domain.exceptions;

public class PasswordIsWrongException extends RuntimeException{
    public PasswordIsWrongException(){
        super();
    }
    public PasswordIsWrongException(String message) {
        super(message);
    }
}
