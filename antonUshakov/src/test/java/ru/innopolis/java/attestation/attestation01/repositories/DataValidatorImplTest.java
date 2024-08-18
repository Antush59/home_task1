package ru.innopolis.java.attestation.attestation01.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.innopolis.java.attestation.attestation01.model.RegisteredUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataValidatorImplTest {

    DataValidator dataValidator = new DataValidatorImpl();

    @Test
    void validateLoginSuccess() {
        //GIVEN
        String login = "Merantush_59RUS";
        //WHEN//THEN
        assertDoesNotThrow(() -> dataValidator.validateLogin(login));
    }

    @ParameterizedTest
    @CsvSource({"Merantush_59RUS!,Некорректный логин!","Merantush_59RUS_PERM_88,Слишком длинный логин!",
    "Merantush,Логин должен содержать буквы и цифры!"})
    void validateLoginFailedParametrized(String login, String expectedMessage) {
        //GIVEN

        //WHEN//THEN
        Exception exception = assertThrows(RuntimeException.class, () -> dataValidator.validateLogin(login));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void validatePasswordSuccess() {
        //GIVEN
        String password = "password_123";
        //WHEN//THEN
        assertDoesNotThrow(() -> dataValidator.validatePassword(password));
    }

    @ParameterizedTest
    @CsvSource({"password_123!,Некорректный пароль!","password,Пароль должен содержать буквы и цифры!",
            "password_123_Russia_88,Слишком длинный пароль!"})
    void validatePasswordFailedParametrized(String password, String expectedMessage) {
        //GIVEN

        //WHEN//THEN
        Exception exception = assertThrows(RuntimeException.class, () -> dataValidator.validatePassword(password));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void validateConfirmPasswordSuccess() {
        //GIVEN
        String password = "pasword_123";
        String confirmPassword = "pasword_123";
        //WHEN//THEN
        assertDoesNotThrow(() -> dataValidator.validateConfirmPassword(password, confirmPassword));
    }

    @Test
    void validateConfirmPasswordFailed() {
        //GIVEN
        String password = "pasword_123";
        String confirmPassword = "pasword_1";
        String expectedMessage = "Пароли не совпадают!";
        //WHEN//THEN
        Exception exception = assertThrows(RuntimeException.class, () -> dataValidator.validateConfirmPassword(password, confirmPassword));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @CsvSource({"Ушаков","Антон"})
    void validateSurnameOrNameSuccess(String name) {
        //GIVEN

        //WHEN//THEN
        assertDoesNotThrow(() -> dataValidator.validateSurnameOrName(name));
    }

    @ParameterizedTest
    @CsvSource({"Ушаков1,Недопустимый символ в ФИО!","Антон2,Недопустимый символ в ФИО!"})
    void validateSurnameOrNameFailed(String name, String expectedMessage) {
        //GIVEN

        //WHEN//THEN
        Exception exception = assertThrows(RuntimeException.class, () -> dataValidator.validateSurnameOrName(name));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @CsvSource({"Владимирович","null"})
    void validatePatronymicSuccess(String patronymic) {
        //GIVEN

        //WHEN//THEN
        assertDoesNotThrow(() -> dataValidator.validatePatronymic(patronymic));
    }

    @Test
    void validatePatronymicFailed() {
        //GIVEN
        String patronymic = "Владимирович!";
        String expectedMessage = "Недопустимый символ в ФИО!";
        //WHEN//THEN
        Exception exception = assertThrows(RuntimeException.class, () -> dataValidator.validatePatronymic(patronymic));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void validateAgeSuccess() {
        //GIVEN
        String age = "26";
        //WHEN//THEN
        assertDoesNotThrow(() -> dataValidator.validateAge(age));
    }

    @ParameterizedTest
    @CsvSource({"-26,Возраст не может быть меньше 0!","131,Врядли столько живут!",
            "a26,Возраст должен содержать только цифры!"})
    void validateAgeFailedNegativeValue(String age, String expectedMessage) {
        //GIVEN

        //WHEN//THEN
        Exception exception = assertThrows(RuntimeException.class, () -> dataValidator.validateAge(age));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void checkingTheLoginInTheListSuccess() {
        //GIVEN
        RegisteredUser user = new RegisteredUser("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2",
                "2023-12-25T19:10:11.556","Nagibator", "SonMomsFriend",
                "SonMomsFriend", "Смирнов", "Евгений", "Васильевич",
                "12", "false");
        List<RegisteredUser> users = List.of(user);
        String login = "Merantush";
        //WHEN//THEN
        assertDoesNotThrow(() -> dataValidator.checkingTheLoginInTheList(login, users));
    }

    @Test
    void checkingTheLoginInTheListFailed() {
        //GIVEN
        RegisteredUser user = new RegisteredUser("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2",
                "2023-12-25T19:10:11.556","Nagibator", "SonMomsFriend",
                "SonMomsFriend", "Смирнов", "Евгений", "Васильевич",
                "12", "false");
        List<RegisteredUser> users = List.of(user);
        String login = "Nagibator";
        String expectedMessage = "Такой логин уже есть!";
        //WHEN//THEN
        Exception exception = assertThrows(RuntimeException.class, () -> dataValidator.checkingTheLoginInTheList(login, users));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}