package ru.innopolis.java.attestation.attestation01.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    private String login;
    private String password;
    private String confirmPassword;
    private String surname;
    private String name;
    private String patronymic;
    private int age;
    private boolean isWorker = false;

    private String id;
    private LocalDateTime dataCreate;

    public User(String login, String password, String confirmPassword, String surname,
                String name, String patronymic,
                String age, String isWorker) {
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.surname = surname;
        this.name = name;
        this.patronymic = setPatronymic(patronymic);
        this.age = setAge(age);
        this.isWorker = setIsWorker(isWorker);
    }

    public User(User user) {
        this.id = addId();
        this.dataCreate = addDateOfCreation();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.confirmPassword = user.getConfirmPassword();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.patronymic = user.getPatronymic();
        this.age = Integer.parseInt(user.getAge());
        this.isWorker = Boolean.parseBoolean(user.isWorker());

    }

    public User(String id, String dateCreate, String login, String password, String confirmPassword, String surname,
                String name, String patronymic,
                String age, String isWorker) {
        this.id = id;
        this.dataCreate = LocalDateTime.parse(dateCreate);
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.surname = surname;
        this.name = name;
        this.patronymic = setPatronymic(patronymic);
        this.age = (int) setAge(age);
        this.isWorker = setIsWorker(isWorker);
    }

    private String addId() {
        return String.valueOf(UUID.randomUUID());
    }

    private LocalDateTime addDateOfCreation() {
        return LocalDateTime.now();
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
        return id + "|" + dataCreate + "|" + login + "|" + password + "|" + confirmPassword + "|" + surname
               + "|" + name + "|" + patronymic + "|" + age + "|" + isWorker;
    }

    public String getId() {
        return id;
    }

    public String getDataCreate() {
        return String.valueOf(dataCreate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
