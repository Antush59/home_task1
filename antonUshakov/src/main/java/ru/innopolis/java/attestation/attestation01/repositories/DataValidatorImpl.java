package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.exceptions.WrongLoginException;
import ru.innopolis.java.attestation.attestation01.exceptions.WrongPasswordException;
import ru.innopolis.java.attestation.attestation01.model.RegisteredUser;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DataValidatorImpl implements DataValidator {

    @Override
    public void validateLogin(String login) throws WrongLoginException {
        if (!login.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongLoginException("Некорректный логин!");
        } else if (login.matches("^[a-zA-Z]+$")) {
            throw new RuntimeException("Логин должен содержать буквы и цифры!");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Слишком длинный логин!");
        }
    }

    @Override
    public void validatePassword(String password) throws WrongPasswordException {
        if (!password.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongPasswordException("Некорректный пароль!");
        } else if (password.matches("^[a-zA-Z]+$")) {
            throw new RuntimeException("Пароль должен содержать буквы и цифры!");
        }
        else if (password.length() > 20) {
            throw new WrongPasswordException("Слишком длинный пароль!");
        }
    }

    @Override
    public void validateConfirmPassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!Objects.equals(confirmPassword, password)) {
            throw new WrongPasswordException("Пароли не совпадают!");
        }
    }

    public void validateSurnameOrName(String value) throws RuntimeException {
        if (!value.matches("^[а-яёА-ЯЁa-zA-Z]+$")) {
            throw new RuntimeException("Недопустимый символ в ФИО!");
        }
    }

    @Override
    public void validatePatronymic(String value) throws RuntimeException {
        if (value == null) {
            return;
        }
        if (!value.matches("^[а-яёА-ЯЁa-zA-Z]+$")) {
            throw new RuntimeException("Недопустимый символ в ФИО!");
        }
    }

    @Override
    public void validateAge(String age) throws RuntimeException {
        int value;

        if (age == null) {
            return;
        }

        try {
            value = Integer.parseInt(age);
        } catch (RuntimeException exception) {
            throw new RuntimeException("Возраст должен содержать только цифры!");
        }

        if (value < 0) {
            throw new RuntimeException("Возраст не может быть меньше 0!");
        } else if (value > 130) {
            throw new RuntimeException("Врядли столько живут!");
        }
    }

    @Override
    public void checkingTheLoginInTheList(String login, List<RegisteredUser> registeredUsers) {

        Optional<RegisteredUser> first = registeredUsers.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();

        if (first.isPresent()) {
            throw new RuntimeException("Такой логин уже есть!");
        }
    }
}
