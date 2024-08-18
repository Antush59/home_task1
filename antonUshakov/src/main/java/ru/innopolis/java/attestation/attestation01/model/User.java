package ru.innopolis.java.attestation.attestation01.model;

import java.util.Objects;

public class User {

    private final String login;
    private final String password;
    private final String confirmPassword;
    private final String surname;
    private final String name;
    private final String patronymic;
    private final int age;
    private boolean isWorker = false;

    public User(String login, String password, String confirmPassword, String surname, String name, String patronymic,
                String age, String isWorker) {
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.surname = surname;
        this.name = name;
        this.patronymic = setPatronymic(patronymic);
        this.age = (int) setAge(age);
        this.isWorker = setIsWorker(isWorker);
    }

    private boolean setIsWorker(String isWorker) {
        if (isWorker.equals("true")) {
            return true;
        } else return false;
    }

    private String setPatronymic(String patronymic) {
        if (patronymic.equals("")) {
            return null;
        } else return patronymic;
    }

    private int setAge(String age) {
        if (age.equals("")) {
            return 0;
        } else return Integer.parseInt(age);
    }

    public String getAge() {
        return String.valueOf(age);
    }

    public String isWorker() {
        return String.valueOf(isWorker);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        if (patronymic == null) {
            return String.valueOf(patronymic);
        }
        return patronymic;
    }

    @Override
    public String toString() {
        return login + "|" + password + "|" + confirmPassword + "|" + surname + "|" +
                                                     name + "|" + patronymic + "|" + age + "|" + isWorker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return age == user.age && isWorker == user.isWorker && Objects.equals(login, user.login) &&
               Objects.equals(password, user.password) && Objects.equals(confirmPassword, user.confirmPassword) &&
               Objects.equals(surname, user.surname) && Objects.equals(name, user.name) &&
               Objects.equals(patronymic, user.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, confirmPassword, surname, name, patronymic, age, isWorker);
    }
}
