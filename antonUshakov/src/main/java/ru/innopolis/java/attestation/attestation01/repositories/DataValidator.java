package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.model.RegisteredUser;

import java.util.List;

public interface DataValidator {

    void validateLogin(String login);

    void validatePassword(String password);

    void validateConfirmPassword(String password, String confirmPassword);

    void validateSurnameOrName(String value);

    void validatePatronymic(String value);

    void validateAge(String age);

    void checkingTheLoginInTheList(String login, List<RegisteredUser> registeredUsers);
}
