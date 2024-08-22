package ru.innopolis.java.attestation.attestation01.exceptions;

public class WrongAgeException extends RuntimeException{
    public WrongAgeException() {
    }

    public WrongAgeException(String message) {
        super(message);
    }
}
