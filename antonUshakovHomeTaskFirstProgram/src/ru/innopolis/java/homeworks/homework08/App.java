package ru.innopolis.java.homeworks.homework08;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {

    public static void main(String[] args) throws IOException {

        Path pathInput = Path.of("antonUshakovHomeTaskFirstProgram/src/ru/innopolis/java/homeworks/homework08/Input.txt");
        Path pathOutput = Path.of("antonUshakovHomeTaskFirstProgram/src/ru/innopolis/java/homeworks/homework08/Output.txt");

        List<String> strings = Files.readAllLines(pathInput, StandardCharsets.UTF_8);
        List<String> listForOutput = new ArrayList<>();


//        Строку из файла конвертируем в данные класса Person
        List<Person> personList = new ArrayList<>();
        String personLine = strings.get(0);
        if (personLine == null) {
            String info = "Данные покупателей отсутствуют";
            listForOutput.add(info);
            return;
        }
        String[] listPersons = personLine.split("; ");
        for (String listPerson : listPersons) {
            Person currentPerson = new Person(listPerson.split(" = "));
            personList.add(currentPerson);
        }

//        Строку из файла конвертируем в данные класса Product
        List<Product> productList = new ArrayList<>();
        String productLine = strings.get(1);
        if (productLine == null) {
            String info = "Данные продуктов отсутствуют";
            listForOutput.add(info);
            return;
        }
        String[] listProducts = productLine.split("; ");
        for (String listProduct : listProducts) {
            Product currentProduct = new Product(listProduct.split(" = "));
            productList.add(currentProduct);
        }

        inputPersonAndProducts(personList, productList, strings, listForOutput, pathOutput);
        outputData(personList, productList, listForOutput, pathOutput);
    }

    //    Ввод с файла данных о покупателе и товаре. Поиск их в списке, вызов метода checkPriceAndMoney,
//    добавление в корзину покупателя товар, который подошел

    private static void inputPersonAndProducts(List<Person> personList, List<Product> productList, List<String> strings,
                                               List<String> listForOutput, Path pathOutput) throws IOException {
        String string;
        for (int i = 2; i < strings.size(); i++) {
            if (Objects.equals(strings.get(i), "END")) {
                break;
            }
            string = strings.get(i);

            for (Person person : personList) {
                String personString = string.substring(0, string.indexOf("-"));

                if (Objects.equals(personString, person.getName())) {
                    for (Product product : productList) {
                        String productString = string.substring(string
                                .indexOf("-") + 1);

                        if (Objects.equals(productString, product.getProductName())) {
                            if (validateParameters(person, product)) {
                            } else if (checkPriceAndMoney(person, product)) {
                                person.addProductToPacket(productString);
                                String info = "Покупатель " + person.getName() + " купил "
                                        + product.getProductName();
                                listForOutput.add(info);
                            } else {
                                String info = person.getName() + " не может себе позволить "
                                        + product.getProductName();
                                listForOutput.add(info);
                            }
                        }
                    }
                }
            }
        }
        Files.write(pathOutput, listForOutput);
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

    //    Вывод данных в файл
    public static void outputData(List<Person> personList, List<Product> productList,
                                  List<String> listForOutput, Path pathOutput) throws IOException {
        for (Person person : personList) {
            for (Product product : productList) {
                if (Objects.equals(product.getProductName(), "")) {
                    String info = "Название продукта неможет быть пустой строкой";
                    listForOutput.add(info);
                    break;
                } else if (product.getPrice() < 0) {
                    String info = "Стоимость продукта не может быть отрицательным числом";
                    listForOutput.add(info);
                    break;
                }
                String listProduct = person.getProductPackage().toString();
                if (Objects.equals(person.getName(), "")) {
                    String info = "Имя не может быть пустой строкой";
                    listForOutput.add(info);
                    break;
                } else if (person.getAmountOfMoney() < 0) {
                    String info = "Деньги не могут быть отрицательными";
                    listForOutput.add(info);
                    break;
                } else if (person.getProductPackage().isEmpty()) {
                    String info = person.getName() + " - Ничего не куплено";
                    listForOutput.add(info);
                    break;
                } else {
                    String info = person.getName() + " - " + listProduct
                            .replace("[", "").replace("]", "");
                    listForOutput.add(info);
                    break;
                }
            }
        }
        Files.write(pathOutput, listForOutput);
    }

    //    Проверка всех исключающих ситуаций:
//    1. Сумма у покупателя не может быть отрицательной
//    2. Имя покупателя не может быть пустой строкой
//    3. Цена продукта не может быть отрицательной
//    4. Название продукта не может быть пустой строкой
    public static boolean validateParameters(Person person, Product product) {
        return Objects.equals(person.getName(), "") || person.getAmountOfMoney() < 0
                || Objects.equals(product.getProductName(), "") || product.getPrice() < 0;
    }
}

