package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.model.RegisteredUser;
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
        RegisteredUser registeredUser;

        if (validate(user, findAll())) {
            registeredUser = new RegisteredUser(user);
        } else {
            return;
        }
        try {
            Files.write(pathLocation, List.of(registeredUser.toString()), StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public RegisteredUser findById(String id) throws RuntimeException {

        List<RegisteredUser> users = findAll();
        for (RegisteredUser user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        throw new RuntimeException("Пользователя с заданным идентификатором не существует!");
    }

    @Override
    public List<RegisteredUser> findAll() {
        List<RegisteredUser> userList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        try {
            stringList = Files.readAllLines(pathLocation);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        for (String s : stringList) {
            String[] stringArray = s.split("\\|");
            RegisteredUser user = new RegisteredUser(stringArray[0], stringArray[1], stringArray[2], stringArray[3],
                    stringArray[4], stringArray[5], stringArray[6], stringArray[7], stringArray[8], stringArray[9]);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public void update(RegisteredUser user) {

        List<RegisteredUser> users = findAll();
        Optional<RegisteredUser> userFromDb = users.stream()
                .filter(userFromDataBase -> userFromDataBase.getId().equals(user.getId()))
                .findFirst();

        if (userFromDb.isEmpty()) {
            System.out.println("""
                    Пользователь отсутствует в списке!"
                    Добавлен новый Пользователь.""");
            users.add(user);
        } else {
            RegisteredUser foundUser = userFromDb.get();
            foundUser = new RegisteredUser(foundUser.getId(), foundUser.getDataCreate(), user.getLogin(),
                    user.getPassword(), user.getConfirmPassword(), user.getSurname(), user.getName(),
                    user.getPatronymic(), user.getAge(), user.isWorker());
            delete(foundUser.getId(), users);
            users.add(foundUser);
        }
        writeOutFile(users);
    }

    @Override
    public void deleteById(String id) {
        List<RegisteredUser> users = findAll();
        RegisteredUser user =  findById(id);
        delete(user.getId(), users);
        writeOutFile(users);
    }

    @Override
    public List<RegisteredUser> findByAge(String age) {
        List<RegisteredUser> users = findAll();
        return users.stream()
                .filter(user -> user.getAge().equals(age))
                .toList();
    }

    @Override
    public List<RegisteredUser> findByIsWorker(String isWorker) {
        List<RegisteredUser> users = findAll();
        return users.stream()
                .filter(user -> user.isWorker().equals(isWorker))
                .toList();
    }

    @Override
    public void deleteAll() {
        List<RegisteredUser> users = findAll();
        users = new ArrayList<>();
        writeOutFile(users);
    }

    private void writeOutFile(List<RegisteredUser> users) {
        try {
            Files.write(pathLocation, users.stream().map(User::toString).toList(), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void delete(String id, List<RegisteredUser> users) {
        for (int i = 0; i < users.size(); i++) {
            RegisteredUser user = users.get(i);
            if (user.getId().equals(id)) {
                users.remove(user);
            }
        }
    }

    private boolean validate(User user, List<RegisteredUser> registeredUsers) {
        try {
            dataValidator.validateLogin(user.getLogin());
            dataValidator.validatePassword(user.getPassword());
            dataValidator.validateConfirmPassword(user.getPassword(), user.getConfirmPassword());
            dataValidator.validateSurnameOrName(user.getName());
            dataValidator.validateSurnameOrName(user.getSurname());
            dataValidator.validatePatronymic(user.getPatronymic());
            dataValidator.validateAge(user.getAge());
            dataValidator.checkingTheLoginInTheList(user.getLogin(), registeredUsers);
            return true;
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
