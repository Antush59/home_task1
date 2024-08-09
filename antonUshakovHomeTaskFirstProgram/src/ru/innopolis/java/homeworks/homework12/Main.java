package ru.innopolis.java.homeworks.homework12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static String[] enteredString = new String[6];
    private static String keyPersonMap;
    private static final Map<String, List<Person>> personMap = new HashMap<>();

    public static void main(String[] args) {

        enteringInformationAboutPerson();
        writePersonToFile();
    }

    private static void enteringInformationAboutPerson() {
        while (true) {
            System.out.println("""
                    Введите данные:
                    [ФИО][дата рождения(дд.мм.гггг)][номер телефона][пол][возраст]
                    Для завершения наберите: END""");
            Scanner scanner = new Scanner(System.in);
            String value = scanner.nextLine();
            if (Objects.equals(value, "END")) {
                break;
            } else if (validateUserInput(value)) {
                try {
                    Person person = new Person(nameVerification(enteredString[0]), nameVerification(enteredString[1]),
                            nameVerification(enteredString[2]), checkingDateOfBirth(enteredString[3]),
                            checkingPhoneNumber(enteredString[4]), checkingGender(enteredString[5]));

                    List<Person> personList = addListPerson(person);

                    personMap.put(keyPersonMap, personList);


                } catch (RuntimeException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
    }

    private static List<Person> addListPerson(Person person) {
        List<Person> personList;

        if (person.getGender() == 'f' && person.getSurname().endsWith("а")) {
            keyPersonMap = person.getSurname().substring(0, person.getSurname().length() - 1);
        } else {
            keyPersonMap = person.getSurname();
        }

        personList = personMap.get(keyPersonMap);

        if (personList == null) {
            personList = new ArrayList<>();
        }
        personList.add(person);

        return personList;
    }

    private static void writePersonToFile() throws RuntimeException {
        try {
            for (Map.Entry<String, List<Person>> entry : personMap.entrySet()) {
                String keyPersonMap = entry.getKey();
                List<Person> personList = entry.getValue();

                Path path = Path.of("antonUshakovHomeTaskFirstProgram/src/ru/innopolis/java/homeworks/homework12/" +
                                    "resources/" + keyPersonMap + "(a).txt");
                Files.write(path, personList.stream().map(Person::toString).toList(), StandardOpenOption.APPEND,
                        StandardOpenOption.CREATE);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Проблемы с чтением или записью в файл!");
        }
    }

    private static boolean validateUserInput(String userInput) {
        try {
            enteredString = validatePersonDataArrayLength(userInput);
            return true;
        } catch (MyEntryException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static String[] validatePersonDataArrayLength(String userInput) throws MyEntryException {
        String[] enteredData = userInput.split(" ");
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
        char ch = value.charAt(0);

        if (ch != FEMALE && ch != MALE) {
            throw new RuntimeException("Введен не верный пол человека!\nВведите 'f' для женщины и 'm' для мужчины");
        } else return ch;
    }
}
