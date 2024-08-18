package ru.innopolis.java.homeworks.homework12Addition.exсeptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
