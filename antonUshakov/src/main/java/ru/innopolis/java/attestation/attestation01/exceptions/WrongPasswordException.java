package ru.innopolis.java.attestation.attestation01.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
