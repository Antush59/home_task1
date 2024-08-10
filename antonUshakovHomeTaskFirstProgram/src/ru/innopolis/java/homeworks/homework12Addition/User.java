package ru.innopolis.java.homeworks.homework12Addition;

public class User {

    private final String login;
    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
               "login='" + login + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
