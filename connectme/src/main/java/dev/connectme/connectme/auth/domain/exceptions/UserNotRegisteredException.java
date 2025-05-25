package dev.connectme.connectme.auth.domain.exceptions;

public class UserNotRegisteredException extends RuntimeException {
    public UserNotRegisteredException(){
        super();
    }
    public UserNotRegisteredException(String message) {
        super(message);
    }
}
