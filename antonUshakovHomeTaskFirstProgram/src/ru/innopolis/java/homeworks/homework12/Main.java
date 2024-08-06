package ru.innopolis.java.homeworks.homework12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static String[] enteredString = new String[6];
    private static Person person;

    public static void main(String[] args) {

        while (true) {
            System.out.println("""
                    Введите данные:
                    [ФИО][дата рождения(дд.мм.гггг)][номер телефона][пол][возраст]
                    Для завершения наберите: END""");
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            if (Objects.equals(data, "END")) {
                break;
            } else if (firstCheck(data)) {
                try {
                    person = new Person(nameVerification(enteredString[0]), nameVerification(enteredString[1]),
                            nameVerification(enteredString[2]), checkingDateOfBirth(enteredString[3]),
                            checkingPhoneNumber(enteredString[4]), checkingGender(enteredString[5]));
                } catch (RuntimeException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }

        System.out.println(person);
    }

    private static boolean firstCheck(String data) {
        try {
            enteredString = dataEntry(data);
            return true;
        } catch (MyEntryException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static String[] dataEntry(String data) throws MyEntryException {

        String[] enteredData = data.split(" ");
        if (enteredData.length > 6) {
            throw new MyEntryException("Внимание! Введено больше данных!");
        } else if (enteredData.length < 6) {
            throw new MyEntryException("Внимание! Введено меньше данных!");
        } else return enteredData;
    }

    private static String nameVerification(String value) throws RuntimeException {
        if (value.matches("^[а-яёА-ЯЁa-zA-Z]+$")) {
            return value;
        } else {
            throw new RuntimeException("Недопустимый символ в ФИО!");
        }
    }

    private static String checkingDateOfBirth(String value) throws RuntimeException {
        LocalDate date;
        LocalDate today = LocalDate.now();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = LocalDate.parse(value, formatter);
        } catch (DateTimeParseException exception) {
            throw new RuntimeException("Не верный формат даты рождения!");
        }

        if (date.isAfter(today)) {
            throw new RuntimeException("Дата рождения введена не правильно!");
        } else return value;
    }

    private static long checkingPhoneNumber(String value) {
        if (value.length() != 11) {
            throw new RuntimeException("Не правильное количество цифр в номере!\nНеобходимо одинацатизначное");
        } else if (value.matches("^[0-9]+$")) {
            return Long.parseLong(value);
        } else {
            throw new RuntimeException("Недопустимый символ в номере телефона!");
        }
    }

    private static char checkingGender(String value) throws RuntimeException {
        final char FEMALE = 'f';
        final char MALE = 'm';
        char ch =value.charAt(0);

        if (ch!=FEMALE && ch!=MALE) {
            throw new RuntimeException("Введен не верный пол человека!\nВведите 'f' для женщины и 'm' для мужчины");
        } else return ch;
    }
}
