package ru.innopolis.java.homeworks.homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

//        Строку из консоли конвертируем в данные класса Person
        System.out.println("Введите список покупателей");
        List<Person> personList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String list = scanner.nextLine();
        String[] listPersons = list.split("; ");
        for (String listPerson : listPersons) {
            Person currentPerson = new Person(listPerson.split(" = "));
            personList.add(currentPerson);
        }

//        Строку из консоли конвертируем в данные класса Product
        System.out.println("Введите список продуктов");
        List<Product> productList = new ArrayList<>();
        String list2 = scanner.nextLine();
        String[] listProducts = list2.split("; ");
        for (String listProduct : listProducts) {
            Product currentProduct = new Product(listProduct.split(" = "));
            productList.add(currentProduct);
        }

        inputPersonAndProducts(personList, productList);
        printAll(personList, productList);
    }

//    Сравниваем количество денег у покупателя с ценой товара
    public static boolean checkPriceAndMoney(Person person, Product product) {
        if (person.getAmountOfMoney() < product.getPrice()) {
            return false;
        } else {
            person.setAmountOfMoney(person.getAmountOfMoney() - product.getPrice());
            return true;
        }
    }

//    Ввод с консоли данных о покупателе и товаре. Поиск их в списке, вызов метода checkPriceAndMoney,
//    добавление в корзину покупателя товар, который подошел
    private static void inputPersonAndProducts(List<Person> personList, List<Product> productList) {
        while (true) {
            System.out.println("Введите данные о покупке. Если их нет, введите END");
            Scanner sc = new Scanner(System.in);
            String scannerPerson = sc.nextLine();

            if (Objects.equals(scannerPerson, "END")) {
                break;
            } else {
                for (Person person : personList) {
                    String personString = scannerPerson.substring(0, scannerPerson.indexOf("-"));

                    if (Objects.equals(personString, person.getName())) {
                        for (Product product : productList) {
                            String productString = scannerPerson.substring(scannerPerson
                                    .indexOf("-") + 1);

                            if (Objects.equals(productString, product.getProductName())) {

                                if (checkAllSituation(personList, productList)) {
                                } else if (checkPriceAndMoney(person, product)) {
                                    person.productPacket(productString);
                                    System.out.println("Покупатель " + person.getName() + " купил "
                                            + product.getProductName());
                                } else {
                                    System.out.println(person.getName() + " не может себе позволить "
                                            + product.getProductName());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

//    Вывод на косоль результатов вноса данных
    public static void printAll(List<Person> personList, List<Product> productList) {
        for (Person person : personList) {
            for (Product product : productList) {
                if (product.getProductName() == "") {
                    System.out.println("Название продукта неможет быть пустой строкой");
                } else if (product.getPrice() < 0) {
                    System.out.println("Стоимость продукта не может быть отрицательным числом");
                }
                String listProduct = person.getProductPackage().toString();
                if (person.getName() == "") {
                    System.out.println("Имя не может быть пустой строкой");
                } else if (person.getAmountOfMoney() < 0) {
                    System.out.println("Деньги не могут быть отрицательными");
                } else if (person.getProductPackage().isEmpty()) {
                    System.out.println(person.getName() + " - Ничего не куплено");
                } else {
                    System.out.println(person.getName() + " - " + listProduct
                            .replace("[", "").replace("]", ""));
                }
            }
        }
    }

//    Проверка всех исключающих ситуаций:
//    1. Сумма у покупателя не может быть отрицательной
//    2. Имя покупателя не может быть пустой строкой
//    3. Цена продукта не может быть отрицательной
//    4. Название продукта не может быть пустой строкой
    public static boolean checkAllSituation(List<Person> persons, List<Product> products) {
        for (Person person : persons) {
            for (Product product : products) {
                return person.getName() == "" || person.getAmountOfMoney() < 0
                        || product.getProductName() == "" || product.getPrice() < 0;
            }
        }
        return false;
    }
}

