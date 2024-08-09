package ru.innopolis.java.homeworks.homework12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Person {

    private final String surname;
    private final String name;
    private final String patronymic;
    private final String dateOfBirth;
    private final long phoneNumber;
    private final char gender;
    private final int age;

    public Person(String surname, String name, String patronymic, String dateOfBirth,
                  long phoneNumber, char gender) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age  = calculateAgeByDateOfBirth(dateOfBirth);
    }

    private int calculateAgeByDateOfBirth(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateOfBirth, formatter);
        LocalDate today = LocalDate.now();
        return today.getYear() - date.getYear();
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "<" + surname + ">" +
                "<" + name + ">" +
                "<" + patronymic + ">" +
                "<" + dateOfBirth + ">" +
                "<" + phoneNumber + ">" +
                "<" + gender + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return phoneNumber == person.phoneNumber && gender == person.gender && age == person.age &&
                Objects.equals(surname, person.surname) && Objects.equals(name, person.name) &&
                Objects.equals(patronymic, person.patronymic) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, dateOfBirth, phoneNumber, gender, age);
    }
}
