package ru.innopolis.java.homeworks.homework12Addition.exсeptions;

public class WrongLoginException extends Exception{
    public WrongLoginException() {
    }

    public WrongLoginException(String message) {
        super(message);
    }
}
