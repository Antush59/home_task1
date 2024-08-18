package ru.innopolis.java.attestation.attestation01.exceptions;

public class WrongLoginException extends RuntimeException{
    public WrongLoginException() {
    }

    public WrongLoginException(String message) {
        super(message);
    }
}
