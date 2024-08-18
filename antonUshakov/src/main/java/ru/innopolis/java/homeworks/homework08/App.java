package ru.innopolis.java.homeworks.homework08;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws IOException {

        List<Product> productList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();

        Path pathInput = Path.of("antonUshakov/src/ru/" +
                "innopolis/java/homeworks/homework08/Input.txt");
        Path pathOutput = Path.of("antonUshakov/src/ru/" +
                "innopolis/java/homeworks/homework08/Output.txt");
        List<String> strings = Files.readAllLines(pathInput, StandardCharsets.UTF_8);
        List<String> listForOutput = new ArrayList<>();

        if (!setPersonList(personList, strings, listForOutput)) {
            listForOutput.add("Список покупателей пуст!");
        } else if (!setProductList(productList, strings, listForOutput)) {
            listForOutput.add("Список продуктов пуст!");
        } else {
            inputPersonAndProducts(personList, productList, strings, listForOutput);
            listForOutput.add(personList.stream()
                    .map(Person::toString)
                    .collect(Collectors.joining("\n"))
            );
        }
        listForOutput
                .forEach(o -> {
                    try {
                        Files.writeString(pathOutput, o, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static boolean setProductList(List<Product> productList, List<String> strings,
                                          List<String> listForOutput) {

        String list2 = strings.get(1);
        String[] listProducts = list2.split("; ");
        for (String listProduct : listProducts) {
            if (checkDataProduct(listProduct, listForOutput)) {
                Product currentProduct = new Product(listProduct.split(" = "));
                productList.add(currentProduct);
            }
        }
        if (productList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean setPersonList(List<Person> personList, List<String> strings,
                                         List<String> listForOutput) {

        String list = strings.get(0);
        String[] listPersons = list.split("; ");
        for (String listPerson : listPersons) {
            if (checkDataPerson(listPerson, listForOutput)) {
                Person currentPerson = new Person(listPerson.split(" = "));
                personList.add(currentPerson);
            }
        }
        if (personList.isEmpty()) {
            return false;
        } else return true;
    }

    //    Ввод с консоли данных о покупателе и товаре. Поиск их в списке, вызов метода checkPriceAndMoney, добавление
    //    в корзину покупателя товар, который подошел

    private static void inputPersonAndProducts(List<Person> personList, List<Product> productList,
                                               List<String> strings, List<String> listForOutput) {
        String string;
        Person per = null;
        Product prod = null;
        for (int i = 2; i < strings.size(); i++) {
            if (Objects.equals(strings.get(i), "END")) {
                break;
            } else {
                string = strings.get(i);
                String personString = string.substring(0, string.indexOf("-"));
                String productString = string.substring(string.indexOf("-") + 1);

                for (Person person : personList) {
                    if (Objects.equals(person.getName(), personString)) {
                        per = person;
                        break;
                    }
                }
                for (Product product : productList) {
                    if (Objects.equals(product.getProductName(), productString)) {
                        prod = product;
                        break;
                    }
                }

                if (per == null) {
                    listForOutput.add("Нет такого покупателя в списке!");
                } else if (prod == null) {
                    listForOutput.add("нет такого продукта в списке!");
                } else {
                    if (per.addProductToPackage(prod)) {
                        listForOutput.add("Покупатель " + per.getName() + " купил "
                                + prod.getProductName() + "\n");
                    } else {
                        listForOutput.add(per.getName() + " не может себе позволить "
                                + prod.getProductName() + "\n");
                    }
                }
            }
        }
    }

    //    Проверка данных Person для добавления объекта
    private static boolean checkDataPerson(String stringPerson, List<String> listForOutput) {
        String[] arrayPerson = stringPerson.split(" = ");
        if (Objects.equals(arrayPerson[0], "")) {
            listForOutput.add("Имя не может быть пустой строкой");
            return false;
        } else if (Integer.parseInt(arrayPerson[1]) < 0) {
            listForOutput.add("Деньги не могут быть отрицательными");
            return false;
        } else {
            return true;
        }
    }

    //    Проверка данных Product для добавления объекта
    private static boolean checkDataProduct(String stringProduct, List<String> listForOutput) {
        String[] arrayProduct = stringProduct.split(" = ");
        if (Objects.equals(arrayProduct[0], "")) {
            listForOutput.add("Название продукта неможет быть пустой строкой");
            return false;
        } else if (Integer.parseInt(arrayProduct[1]) < 0) {
            listForOutput.add("Стоимость продукта не может быть отрицательным числом");
            return false;
        } else return true;

    }
}