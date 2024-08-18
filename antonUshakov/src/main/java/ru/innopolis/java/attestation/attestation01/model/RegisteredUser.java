package ru.innopolis.java.attestation.attestation01.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class RegisteredUser  extends User{

    private final String id;
    private final LocalDateTime dataCreate;

    public RegisteredUser(User user) {
        super(user.getLogin(), user.getPassword(), user.getConfirmPassword(), user.getSurname(), user.getName(),
                user.getPatronymic(), user.getAge(), user.isWorker());
        this.id = addId();
        this.dataCreate = addDateOfCreation();
    }

    public RegisteredUser(String id,String dataCreate, String login, String password, String confirmPassword, String surname,
                          String name, String patronymic, String age, String isWorker) {
        super(login, password, confirmPassword, surname, name, patronymic, age, isWorker);
        this.id = id;
        this.dataCreate = LocalDateTime.parse(dataCreate);
    }

    private String addId() {
        return String.valueOf(UUID.randomUUID());
    }

    private LocalDateTime addDateOfCreation() {
        return LocalDateTime.now();
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
        if (!(o instanceof RegisteredUser that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return id + "|" + dataCreate + "|" + super.toString();
    }
}
