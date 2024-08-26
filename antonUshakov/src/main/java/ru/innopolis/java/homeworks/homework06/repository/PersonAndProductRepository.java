package ru.innopolis.java.homeworks.homework06.repository;

import ru.innopolis.java.homeworks.homework06.models.Person;
import ru.innopolis.java.homeworks.homework06.models.Product;

import java.util.List;

public interface PersonAndProductRepository {

    void setPersonList(List<Person> personList, String personString);

    void setProductList(List<Product> productList, String productString);

    boolean checkDataPerson(String stringPerson);

    boolean checkDataProduct(String stringProduct);

    void addingProductsToProductsPackage(List<Person> personList, List<Product> productList, String purchase);
}
