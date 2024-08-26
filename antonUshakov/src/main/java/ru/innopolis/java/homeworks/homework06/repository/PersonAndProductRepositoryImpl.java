package ru.innopolis.java.homeworks.homework06.repository;

import ru.innopolis.java.homeworks.homework06.exceptions.PersonDataException;
import ru.innopolis.java.homeworks.homework06.exceptions.ProductDataException;
import ru.innopolis.java.homeworks.homework06.models.Person;
import ru.innopolis.java.homeworks.homework06.models.Product;

import java.util.List;
import java.util.Objects;

public class PersonAndProductRepositoryImpl implements PersonAndProductRepository {

    @Override
    public void setPersonList(List<Person> personList, String personString) {

        String[] listPersons = personString.split("; ");
        for (String listPerson : listPersons) {
            try {
                checkDataPerson(listPerson);
                Person currentPerson = new Person(listPerson.split(" = "));
                personList.add(currentPerson);
            } catch (PersonDataException exception) {
                System.out.println(exception.getMessage());
            }
        }
        if (personList.isEmpty()) {
            System.out.println("Список покупателей пуст");
        }
    }

    @Override
    public void setProductList(List<Product> productList, String productString) {

        String[] listProducts = productString.split("; ");
        for (String listProduct : listProducts) {
            try {
                checkDataProduct(listProduct);
                Product currentProduct = new Product(listProduct.split(" = "));
                productList.add(currentProduct);
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
        }
        if (productList.isEmpty()) {
            System.out.println("Список продуктов пуст");
        }
    }

    @Override
    public boolean checkDataPerson(String stringPerson) throws PersonDataException {
        String[] arrayPerson = stringPerson.split(" = ");
        try {
            Integer.parseInt(arrayPerson[1]);
        } catch (NumberFormatException exception) {
            throw new PersonDataException("Не верный формат наличных!");
        }
        if (Objects.equals(arrayPerson[0], "")) {
            throw new PersonDataException("Имя не может быть пустой строкой");
        } else if (Integer.parseInt(arrayPerson[1]) < 0) {
            throw new PersonDataException("Наличные не могут быть отрицательными");
        } else return true;

    }

    @Override
    public boolean checkDataProduct(String stringProduct) {
        String[] arrayProduct = stringProduct.split(" = ");
        try {
            Integer.parseInt(arrayProduct[1]);
        } catch (NumberFormatException exception) {
            throw new ProductDataException("Не верный формат цены!");
        }
        if (Objects.equals(arrayProduct[0], "")) {
            throw new ProductDataException("Название продукта неможет быть пустой строкой");
        } else if (Integer.parseInt(arrayProduct[1]) < 0) {
            throw new ProductDataException("Стоимость продукта не может быть отрицательным числом");
        } else return true;
    }

    @Override
    public void addingProductsToProductsPackage(List<Person> personList, List<Product> productList,
                                                String purchase) {
        Person validatePerson = new Person();
        Product validatePRoduct = new Product();
        String personString = purchase.substring(0, purchase.indexOf("-"));
        String productString = purchase.substring(purchase.indexOf("-") + 1);

        for (Person person : personList) {
            if (Objects.equals(person.getName(), personString)) {
                validatePerson = person;
                for (Product product : productList) {
                    if (Objects.equals(product.getProductName(), productString)) {
                        validatePRoduct = product;
                        if (person.getAmountOfMoney() < product.getPrice()) {
                            System.out.println(person.getName() + " не может себе позволить "
                                               + product.getProductName());
                        } else {
                            person.setAmountOfMoney(person.getAmountOfMoney() - product.getPrice());
                            person.getProductPackage().add(product);
                            System.out.println("Покупатель " + person.getName() + " купил "
                                               + product.getProductName());
                        }
                        break;
                    }
                }
                break;
            }
        }
        if (validatePerson == null) {
            System.out.println("Нет такого покупателя в списке!");
        } else if (validatePRoduct == null) {
            System.out.println("нет такого продукта в списке!");
        }
    }
}
