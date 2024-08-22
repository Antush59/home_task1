package ru.innopolis.java.attestation.attestation01;

import ru.innopolis.java.attestation.attestation01.model.User;
import ru.innopolis.java.attestation.attestation01.repositories.DataValidatorImpl;
import ru.innopolis.java.attestation.attestation01.repositories.UsersRepositoryFileImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class App {

    public static final Path pathLocation =
            Path.of("D:\\anton\\idea_projects\\home_task1\\antonUshakov\\src\\main\\resources\\UsersFile.txt");

    public static void main(String[] args) {

        UsersRepositoryFileImpl usersRepository = new UsersRepositoryFileImpl(new DataValidatorImpl(), pathLocation);

        User user1 = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2", "2023-12-25T19:10:11.556",
                "Razrushitel1988", "ChinaNumberOne1", "ChinaNumberOne1",
                "Li", "Son", "", "", "true");
        User user2 = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2", "2023-12-25T19:10:11.556",
                "Razrushitel1988", "MamkinKiller88", "MamkinKiller88",
                "Никулин", "Николай", "Николаевич", "25", "false");
        User user3 = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2", "2023-12-25T19:10:11.556",
                "Nagibator2004", "SonMomsFriend04", "SonMomsFriend04",
                "Смирнов", "Евгений", "Васильевич", "12", "false");

        usersRepository.create(user1);
        usersRepository.create(user2);
        usersRepository.create(user3);

        try {
            System.out.println("Пользователь по заданному id:");
            System.out.println(usersRepository.findById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2"));
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("Список пользователей:");
        List<User> users = usersRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("Список пуст!");
        } else {
            users.forEach(System.out::println);
        }

        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2",
                "2023-12-25T19:10:11.556", "VityaAK", "tratatata_96",
                "tratatata_96", "Калашников", "Виктор", "Сергеевич", "36",
                "false");
        usersRepository.update(user);

        usersRepository.deleteById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2");

        String age = "12";
        System.out.println("Список пользователь отсортированный по возрасту:");
        usersRepository.findByAge(age).forEach(System.out::println);

        String isWorker = "true";
        System.out.println("Список пользователь работающих в данной компании:");
        usersRepository.findByIsWorker(isWorker).forEach(System.out::println);

        usersRepository.deleteAll();

        try {
            Files.copy(Path.of("home_task1/antonUshakov/src/main/resources/firstUser.txt"), pathLocation,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
