package ru.innopolis.java.homeworks.homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();

//        Строку из консоли конвертируем в данные класса Person
        System.out.println("Введите список покупателей: ");
        setPersonList(personList);

//        Строку из консоли конвертируем в данные класса Product
        System.out.println("Введите список продуктов: ");
        setProductList(productList);

        System.out.println("Введите данные о покупке. Если их нет, введите END");
        inputPersonAndProducts(personList, productList);

        personList.forEach(System.out::println);
    }

    private static void setProductList(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String list2 = scanner.nextLine();
            String[] listProducts = list2.split("; ");
            for (String listProduct : listProducts) {
                if (checkDataProduct(listProduct)) {
                    Product currentProduct = new Product(listProduct.split(" = "));
                    productList.add(currentProduct);
                }
            }
            if (productList.isEmpty()) {
                System.out.println("Список пуст, введите корректные данные: ");
            } else {
                break;
            }
        }
    }

    private static void setPersonList(List<Person> personList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String list = scanner.nextLine();
            String[] listPersons = list.split("; ");
            for (String listPerson : listPersons) {
                if (checkDataPerson(listPerson)) {
                    Person currentPerson = new Person(listPerson.split(" = "));
                    personList.add(currentPerson);
                }
            }
            if (personList.isEmpty()) {
                System.out.println("Список пуст, введите корректные данные: ");
            } else {
                break;
            }
        }
    }

    //    Ввод с консоли данных о покупателе и товаре. Поиск их в списке, вызов метода checkPriceAndMoney, добавление
    //    в корзину покупателя товар, который подошел

    private static void inputPersonAndProducts(List<Person> personList, List<Product> productList) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String scannerPerson = sc.nextLine();
            Person per = null;
            Product prod = null;

            if (Objects.equals(scannerPerson, "END")) {
                break;
            } else {
                String personString = scannerPerson.substring(0, scannerPerson.indexOf("-"));
                String productString = scannerPerson.substring(scannerPerson.indexOf("-") + 1);

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
                    System.out.println("Нет такого покупателя в списке!");
                } else if (prod == null) {
                    System.out.println("нет такого продукта в списке!");
                } else {
                    if (per.addProductToPackage(prod)) {
                        System.out.println("Покупатель " + per.getName() + " купил "
                                + prod.getProductName());
                    } else {
                        System.out.println(per.getName() + " не может себе позволить "
                                + prod.getProductName());
                    }
                }
            }
        }
    }

    //    Проверка данных Person для добавления объекта
    private static boolean checkDataPerson(String stringPerson) {
        String[] arrayPerson = stringPerson.split(" = ");
        if (Objects.equals(arrayPerson[0], "")) {
            System.out.println("Имя не может быть пустой строкой");
            return false;
        } else if (Integer.parseInt(arrayPerson[1]) < 0) {
            System.out.println("Деньги не могут быть отрицательными");
            return false;
        } else {
            return true;
        }
    }

    //    Проверка данных Product для добавления объекта
    private static boolean checkDataProduct(String stringProduct) {
        String[] arrayProduct = stringProduct.split(" = ");
        if (Objects.equals(arrayProduct[0], "")) {
            System.out.println("Название продукта неможет быть пустой строкой");
            return false;
        } else if (Integer.parseInt(arrayProduct[1]) < 0) {
            System.out.println("Стоимость продукта не может быть отрицательным числом");
            return false;
        } else return true;

    }
}