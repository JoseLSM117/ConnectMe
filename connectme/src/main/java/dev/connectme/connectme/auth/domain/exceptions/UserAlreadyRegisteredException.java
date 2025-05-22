package dev.connectme.connectme.auth.domain.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
        super();
    }
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}