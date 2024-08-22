package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.exceptions.WrongAgeException;
import ru.innopolis.java.attestation.attestation01.exceptions.WrongInitialsException;
import ru.innopolis.java.attestation.attestation01.exceptions.WrongLoginException;
import ru.innopolis.java.attestation.attestation01.exceptions.WrongPasswordException;
import ru.innopolis.java.attestation.attestation01.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DataValidatorImpl implements DataValidator {

    @Override
    public void validateLogin(String login) throws WrongLoginException {
        if (!login.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongLoginException("Некорректный логин!");
        } else if (login.matches("^[a-zA-Z]+$")) {
            throw new WrongLoginException("Логин должен содержать буквы и цифры!");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Слишком длинный логин!");
        }
    }

    @Override
    public void validatePassword(String password) throws WrongPasswordException {
        if (!password.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongPasswordException("Некорректный пароль!");
        } else if (password.matches("^[a-zA-Z]+$")) {
            throw new WrongPasswordException("Пароль должен содержать буквы и цифры!");
        } else if (password.length() > 20) {
            throw new WrongPasswordException("Слишком длинный пароль!");
        }
    }

    @Override
    public void validateConfirmPassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!Objects.equals(confirmPassword, password)) {
            throw new WrongPasswordException("Пароли не совпадают!");
        }
    }

    public void validateSurnameOrName(String value) throws WrongInitialsException {
        if (!value.matches("^[а-яёА-ЯЁa-zA-Z]+$")) {
            throw new WrongInitialsException("Недопустимый символ в ФИО!");
        }
    }

    @Override
    public void validatePatronymic(String value) throws WrongInitialsException {
        if (value == null) {
            return;
        }
        if (!value.matches("^[а-яёА-ЯЁa-zA-Z]+$")) {
            throw new WrongInitialsException("Недопустимый символ в ФИО!");
        }
    }

    @Override
    public void validateAge(String age) throws WrongAgeException {
        int value;

        if (age == null) {
            return;
        }

        try {
            value = Integer.parseInt(age);
        } catch (NumberFormatException exception) {
            throw new WrongAgeException("Возраст должен содержать только цифры!");
        }

        if (value < 0) {
            throw new WrongAgeException("Возраст не может быть меньше 0!");
        } else if (value > 100) {
            throw new WrongAgeException("Врядли столько живут!");
        }
    }

    @Override
    public void checkingTheLoginInTheList(String login, List<User> users) {

        Optional<User> first = users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();

        if (first.isPresent()) {
            throw new WrongLoginException("Такой логин уже есть!");
        }
    }
}
