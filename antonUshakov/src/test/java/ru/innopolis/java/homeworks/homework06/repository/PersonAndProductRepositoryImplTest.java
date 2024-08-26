package ru.innopolis.java.homeworks.homework06.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.innopolis.java.homeworks.homework06.exceptions.PersonDataException;
import ru.innopolis.java.homeworks.homework06.exceptions.ProductDataException;
import ru.innopolis.java.homeworks.homework06.models.Person;
import ru.innopolis.java.homeworks.homework06.models.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonAndProductRepositoryImplTest {

    PersonAndProductRepositoryImpl personAndProductRepository = new PersonAndProductRepositoryImpl();

    @Test
    void setPersonList() {
        String value = "Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10";
        List<Person> listPerson = new ArrayList<>();
        personAndProductRepository.setPersonList(listPerson, value);
        assertEquals(listPerson.size(), 3);
    }

    @Test
    void setProductList() {
        String value = "Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150";
        List<Product> listProduct = new ArrayList<>();
        personAndProductRepository.setProductList(listProduct, value);
        assertEquals(listProduct.size(), 5);
    }

    @Test
    void checkDataPersonSuccess() {
        //GIVEN
        String value = "Павел Андреевич = 10000";
        //WHEN//THEN
        assertTrue(personAndProductRepository.checkDataPerson(value));
    }

    @Test
    void checkDataPersonFailedName() {
        //GIVEN
        String expectedMessage = "Имя не может быть пустой строкой";
        String value = " = 10000";
        //WHEN//THEN
        Exception exception = assertThrows(PersonDataException.class, () -> personAndProductRepository.checkDataPerson(value));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void checkDataPersonFailedCash() {
        //GIVEN
        String expectedMessage = "Не верный формат наличных!";
        String value = "Павел Андреевич = 1a0000";
        //WHEN//THEN
        Exception exception = assertThrows(PersonDataException.class, () -> personAndProductRepository.checkDataPerson(value));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void checkDataPersonFailedCashNegative() {
        //GIVEN
        String expectedMessage = "Наличные не могут быть отрицательными";
        String value = "Павел Андреевич = -10000";
        //WHEN//THEN
        Exception exception = assertThrows(PersonDataException.class, () -> personAndProductRepository.checkDataPerson(value));
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @Disabled
    void checkDataProductSuccess() {
        //GIVEN
        String value = "Торт = 1000";
        //WHEN//THEN
        assertTrue(personAndProductRepository.checkDataProduct(value));
    }

    @Test
    void checkDataProductFailedProductName() {
        //GIVEN
        String expectedMessage = "Название продукта неможет быть пустой строкой";
        String value = " = 1000";
        //WHEN
        Exception exception = assertThrows(ProductDataException.class, () -> personAndProductRepository.checkDataProduct(value));
        //THEN
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void checkDataProductFailedProductPrice() {
        //GIVEN
        String expectedMessage = "Не верный формат цены!";
        String value = "Торт = 10a00";
        //WHEN
        Exception exception = assertThrows(ProductDataException.class, () -> personAndProductRepository.checkDataProduct(value));
        //THEN
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void checkDataProductFailedProductPriceNegative() {
        //GIVEN
        String expectedMessage = "Стоимость продукта не может быть отрицательным числом";
        String value = "Торт = -1000";
        //WHEN
        Exception exception = assertThrows(ProductDataException.class, () -> personAndProductRepository.checkDataProduct(value));
        //THEN
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void addingProductsToProductsPackage() {
        //GIVEN
        List<Person> personList = new ArrayList<>();
        Person person = new Person("Alisa", 1000);
        personList.add(person);
        List<Product> productList = new ArrayList<>();
        Product product = new Product("Хлеб", 40);
        productList.add(product);
        String purchase = "Alisa-Хлеб";
        //WHEN
        personAndProductRepository.addingProductsToProductsPackage(personList, productList, purchase);
        //THEN
        assertEquals(personList.size(), 1);
        assertEquals(personList.get(0).getProductPackage().size(), 1);
    }

}