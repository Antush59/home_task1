package ru.innopolis.java.attestation.attestation01.exceptions;

public class WrongInitialsException extends RuntimeException{
    public WrongInitialsException() {
    }

    public WrongInitialsException(String message) {
        super(message);
    }
}
