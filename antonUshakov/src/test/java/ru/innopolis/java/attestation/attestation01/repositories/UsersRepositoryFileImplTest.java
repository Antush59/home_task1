package ru.innopolis.java.attestation.attestation01.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.java.attestation.attestation01.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsersRepositoryFileImplTest {

    DataValidator dataValidator = Mockito.mock(DataValidator.class);
    String fileName = "home_task1/antonUshakov/src/test/resources/testFileUsers.txt";
    UsersRepositoryFileImpl usersRepositoryFile = new UsersRepositoryFileImpl(dataValidator, Path.of(fileName));

    @Test
    void createSuccess() throws IOException {
        //GIVEN
        DataValidator dataValidator = Mockito.mock(DataValidator.class);
        String fileNameTest = "home_task1/antonUshakov/src/test/resources/UsersFileTest.txt";
        UsersRepositoryFileImpl usersRepositoryFile = new UsersRepositoryFileImpl(dataValidator, Path.of(fileNameTest));

        String id = "f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2";
        String dateCreate = "2023-12-25T19:10:11.556";
        String login = "login12";
        String password = "pass1234";
        String surname = "surname";
        String name = "name";
        String patronymic = "patronymic";
        String age = "20";
        String isWorker = "true";
        User user = new User(login, password, password, surname, name, patronymic, age, isWorker);

        //WHEN
        usersRepositoryFile.create(user);

        //THEN
        List<String> actualUserLines = Files.readAllLines(Path.of(fileNameTest));

        assertNotNull(actualUserLines);
        assertEquals(1, actualUserLines.size());

        String actualUser = actualUserLines.get(0);
        assertNotNull(actualUser);

        String[] userAttributes = actualUser.split("\\|");
        assertEquals(10, userAttributes.length);
        String createId = userAttributes[0];
        String createdDateTime = userAttributes[1];
        String createdLogin = userAttributes[2];
        String pass = userAttributes[3];
        String pass2 = userAttributes[4];
        String createdSurname = userAttributes[5];
        String createdName = userAttributes[6];
        String createdPatr = userAttributes[7];
        String createdAge = userAttributes[8];
        String employee = userAttributes[9];

        assertNotNull(createId);

        assertNotNull(createdDateTime);
        LocalDateTime created = LocalDateTime.parse(createdDateTime);

        assertAll(
                () -> assertEquals(LocalDate.now(), created.toLocalDate()),
                () -> assertEquals(login, createdLogin),
                () -> assertEquals(password, pass),
                () -> assertEquals(password, pass2),
                () -> assertEquals(surname, createdSurname),
                () -> assertEquals(name, createdName),
                () -> assertEquals(patronymic, createdPatr),
                () -> assertEquals(age, createdAge),
                () -> assertEquals(isWorker, employee)
        );

        Mockito.verify(dataValidator).validateLogin(login);
        Mockito.verify(dataValidator).validatePassword(password);
        Mockito.verify(dataValidator).validateConfirmPassword(password, password);
        Mockito.verify(dataValidator).validateSurnameOrName(name);
        Mockito.verify(dataValidator).validateSurnameOrName(surname);
        Mockito.verify(dataValidator).validatePatronymic(patronymic);
        Mockito.verify(dataValidator).validateAge(age);
        Mockito.verify(dataValidator).checkingTheLoginInTheList(login, Collections.emptyList());

        Files.delete(Path.of(fileNameTest));
    }

    @Test
    void findByIdSuccess() {
        //GIVEN
        String byId = "3b2b26ca-e3f5-4096-b7be-8361cb2d9b32";
        //WHEN
        User user = usersRepositoryFile.findById(byId);
        //THEN
        assertNotNull(user);

        assertAll("Проверка на данные пользователя",
                () -> assertEquals("3b2b26ca-e3f5-4096-b7be-8361cb2d9b32", user.getId()),
                () -> assertEquals("2024-08-17T15:49:32.890657200", user.getDataCreate()),
                () -> assertEquals("Razrushitel1988", user.getLogin()),
                () -> assertEquals("ChinaNumberOne1", user.getPassword()),
                () -> assertEquals("ChinaNumberOne1", user.getConfirmPassword()),
                () -> assertEquals("Li", user.getSurname()),
                () -> assertEquals("Son", user.getName()),
                () -> assertEquals("null", user.getPatronymic()),
                () -> assertEquals("36", user.getAge()),
                () -> assertEquals("true", user.isWorker())
        );
    }

    @Test
    void findByIdFailed() {
        //GIVEN
        String byId = "3b2b26ca-e3f5-4096-b7be-8361cb2d9b";
        String expectedMessage = "Пользователя с заданным идентификатором не существует!";
        //WHEN//THEN
        Exception exception = assertThrows(RuntimeException.class, () -> usersRepositoryFile.findById(byId));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void findAllSuccess() throws IOException {
        //GIVEN

        //WHEN
        List<User> users = usersRepositoryFile.findAll();
        //THEN
        assertEquals(Files.readAllLines(Path.of(fileName)).size(), users.size());

        assertAll(
                () -> assertEquals("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2", users.get(0).getId()),
                () -> assertEquals("3b2b26ca-e3f5-4096-b7be-8361cb2d9b32", users.get(1).getId()),
                () -> assertEquals("48457501-2e46-44ea-8631-76165aeb5cfe", users.get(2).getId())
        );
    }

    @Test
    void updateSuccess() throws IOException {
        //Given
        User user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2", "2023-12-25T19:10:11.556",
                "VityaAK", "tratatata", "tratatata", "Калашников",
                "Виктор", "Сергеевич", "36", "false");
        User creatUser = usersRepositoryFile.findById(user.getId());
        //WHEN
        usersRepositoryFile.update(user);
        //THEN
        assertEquals(Files.readAllLines(Path.of(fileName)).size(), 3);

        assertAll(
                () -> assertEquals(user.getId(), creatUser.getId()),
                () -> assertEquals(user.getDataCreate(), creatUser.getDataCreate()),
                () -> assertNotEquals(user.getLogin(), creatUser.getLogin()),
                () -> assertNotEquals(user.getPassword(), creatUser.getPassword()),
                () -> assertNotEquals(user.getConfirmPassword(), creatUser.getConfirmPassword()),
                () -> assertNotEquals(user.getSurname(), creatUser.getSurname()),
                () -> assertEquals(user.getName(), creatUser.getName()),
                () -> assertNotEquals(user.getPatronymic(), creatUser.getPatronymic()),
                () -> assertNotEquals(user.getAge(), creatUser.getAge()),
                () -> assertEquals(user.isWorker(), creatUser.isWorker())
        );

        Files.copy(Path.of("home_task1/antonUshakov/src/test/resources/dataForTesting.txt"), Path.of(fileName),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void deleteByIdSuccess() throws IOException {
        //GIVEN

        String byId = "48457501-2e46-44ea-8631-76165aeb5cfe";
        //WHEN
        usersRepositoryFile.deleteById(byId);
        //THEN
        assertEquals(Files.readAllLines(Path.of(fileName)).size(), 2);

        Files.copy(Path.of("home_task1/antonUshakov/src/test/resources/dataForTesting.txt"), Path.of(fileName),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findByAgeSuccess() {
        //GIVEN

        String age = "25";
        //WHEN
        List<User> users = usersRepositoryFile.findByAge(age);
        //THEN
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getId(), "f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2");
    }

    @Test
    void findByIsWorkerSuccess() {
        //GIVEN

        String isWorker = "false";
        //WHEN
        List<User> users = usersRepositoryFile.findByIsWorker(isWorker);
        //THEN
        assertEquals(users.size(), 2);

        assertAll(
                () -> assertEquals(users.get(0).isWorker(), isWorker),
                () -> assertEquals(users.get(1).isWorker(), isWorker)
        );
    }

    @Test
    void deleteAllSuccess() throws IOException {
        //GIVEN

        //WHEN
        usersRepositoryFile.deleteAll();
        //THEN
        assertTrue(Files.readAllLines(Path.of(fileName)).isEmpty());

        Files.copy(Path.of("home_task1/antonUshakov/src/test/resources/dataForTesting.txt"), Path.of(fileName),
                StandardCopyOption.REPLACE_EXISTING);
    }
}