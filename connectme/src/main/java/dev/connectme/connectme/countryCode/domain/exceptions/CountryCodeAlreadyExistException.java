package dev.connectme.connectme.countryCode.domain.exceptions;

public class CountryCodeAlreadyExistException extends RuntimeException{
    public CountryCodeAlreadyExistException() {
        super();
    }
    public CountryCodeAlreadyExistException(String message){
        super(message);
    }
}
