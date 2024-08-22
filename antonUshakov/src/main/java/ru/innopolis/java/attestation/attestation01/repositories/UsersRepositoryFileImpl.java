package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;


public class UsersRepositoryFileImpl implements UsersRepository {

    private final DataValidator dataValidator;
    private final Path pathLocation;

    public UsersRepositoryFileImpl(DataValidator dataValidator, Path pathLocation) {
        this.dataValidator = requireNonNull(dataValidator);
        this.pathLocation = pathLocation;
    }

    @Override
    public void create(User user) {
        User validateUser;

        if (validate(user, findAll())) {
            validateUser = new User(user);
        } else {
            return;
        }
        try {
            Files.write(pathLocation, List.of(validateUser.toString()), StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public User findById(String id) throws RuntimeException {

        List<User> users = findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        throw new RuntimeException("Пользователя с заданным идентификатором не существует!");
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        try {
            stringList = Files.readAllLines(pathLocation);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        for (String s : stringList) {
            String[] stringArray = s.split("\\|");
            User user = new User(stringArray[0], stringArray[1], stringArray[2], stringArray[3],
                    stringArray[4], stringArray[5], stringArray[6], stringArray[7], stringArray[8], stringArray[9]);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public void update(User user) {

        List<User> users = findAll();
        Optional<User> userFromDb = users.stream()
                .filter(userFromDataBase -> userFromDataBase.getId().equals(user.getId()))
                .findFirst();

        if (userFromDb.isEmpty()) {
            System.out.println("""
                    Пользователь отсутствует в списке!"
                    Добавлен новый Пользователь.""");
            users.add(user);
        } else {
            User foundUser = userFromDb.get();
            foundUser = new User(foundUser.getId(), foundUser.getDataCreate(), user.getLogin(),
                    user.getPassword(), user.getConfirmPassword(), user.getSurname(), user.getName(),
                    user.getPatronymic(), user.getAge(), user.isWorker());
            delete(foundUser.getId(), users);
            users.add(foundUser);
        }
        writeOutFile(users);
    }

    @Override
    public void deleteById(String id) {
        List<User> users = findAll();
        User user = findById(id);
        delete(user.getId(), users);
        writeOutFile(users);
    }

    @Override
    public List<User> findByAge(String age) {
        List<User> users = findAll();
        return users.stream()
                .filter(user -> user.getAge().equals(age))
                .toList();
    }

    @Override
    public List<User> findByIsWorker(String isWorker) {
        List<User> users = findAll();
        return users.stream()
                .filter(user -> user.isWorker().equals(isWorker))
                .toList();
    }

    @Override
    public void deleteAll() {
        List<User> users = findAll();
        users = new ArrayList<>();
        writeOutFile(users);
    }

    private void writeOutFile(List<User> users) {
        try {
            Files.write(pathLocation, users.stream().map(User::toString).toList(), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void delete(String id, List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(id)) {
                users.remove(user);
            }
        }
    }

    private boolean validate(User user, List<User> users) {
        try {
            dataValidator.validateLogin(user.getLogin());
            dataValidator.validatePassword(user.getPassword());
            dataValidator.validateConfirmPassword(user.getPassword(), user.getConfirmPassword());
            dataValidator.validateSurnameOrName(user.getName());
            dataValidator.validateSurnameOrName(user.getSurname());
            dataValidator.validatePatronymic(user.getPatronymic());
            dataValidator.validateAge(user.getAge());
            dataValidator.checkingTheLoginInTheList(user.getLogin(), users);
            return true;
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
