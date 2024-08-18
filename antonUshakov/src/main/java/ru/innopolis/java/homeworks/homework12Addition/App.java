package ru.innopolis.java.homeworks.homework12Addition;

import ru.innopolis.java.homeworks.homework12Addition.exсeptions.WrongLoginException;
import ru.innopolis.java.homeworks.homework12Addition.exсeptions.WrongPasswordException;

import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        String login;
        String password;
        String confirmPassword;
        User user;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                    Введите логин!
                    Логин должен состоять из латинских букв, цифр и нижнего подчеркивания.
                    Количество символов не более 20!""");
            login = scanner.nextLine();

            System.out.println("""
                    Введите пароль!
                    Пароль должен состоять из латинских букв, цифр и нижнего подчеркивания.
                    Количество символов не более 20!""");
            password = scanner.nextLine();

            System.out.println("Подтвердите  пароль!");
            confirmPassword = scanner.nextLine();

            if (validate(login, password, confirmPassword)) {
                user = new User(login, password);
                break;
            }
        }
        System.out.println(user);
    }

    private static boolean validate(String login, String password, String confirmPassword) {
        try {
            validateLogin(login);
        } catch (WrongLoginException e) {
            System.out.println(e.getMessage());
            return false;
        }
        try {
            validatePassword(password, confirmPassword);
            return true;
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static void validateLogin(String login) throws WrongLoginException {
        if (!login.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongLoginException("Некорректный логин!");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Слишком длинный логин!");
        }
    }

    private static void validatePassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongPasswordException("Некорректный пароль");
        } else if (password.length() > 20) {
            throw new WrongPasswordException("Слишком длинный пароль!");
        }

        if (!Objects.equals(confirmPassword, password)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }
}
