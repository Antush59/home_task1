package ru.innopolis.java.homeworks.homework06.exceptions;

public class PersonDataException extends RuntimeException {
    public PersonDataException() {
    }

    public PersonDataException(String message) {
        super(message);
    }
}
