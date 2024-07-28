package ru.innopolis.java.homeworks.homework07Addition;

import java.util.*;
import java.util.stream.Collectors;

import static ru.innopolis.java.homeworks.homework07Addition.Person.validate;


public class App {

    public static void main(String[] args) {

//        Создаем массивы данных классов Product и DiscountProduct
        List<Product> products = new ArrayList<>();
        List<DiscountProduct> discountProducts = new ArrayList<>();

//        Вводим с консоли данные о продуктах. Если с консоли введено "END", то цикл прерывается.
//        Для ввода данных используется формат [Название продукта] = [Цена продукта], [если есть, то скидка продукта]
        enteringProducts(products, discountProducts);

//        Проверяем пусто или нет массив, если нет, то выполняем метод printAll
        if (products.isEmpty()) {
            System.out.println("обычные продукты не внесены.");
        } else {
            printAllProducts(products, "Обычные продукты");
        }

        if (discountProducts.isEmpty()) {
            System.out.println("Акционные продукты не внесены.");
        } else {
            printAllProducts(discountProducts, "Акционные продукты");
        }

//        Ввод данных покупателей в Map и проверка на правильность ввода
        Map<String, Person> personMap = enterPersons();

//        Ввод данных покупателя и товара для покупки
        inputPersonAndProduct(personMap, products, discountProducts);

//        Ввывод Данных после покупок. Какой покупатель и что купил.
        printFinal(personMap);
    }

    public static void enteringProducts(List<Product> products, List<DiscountProduct> discountProducts) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные продуктов в формате: \n" + "[Имя продукта] = [Цена продукта], " +
                "[Скидка товара, если есть], [true-товар только для совершеннолетних или false-товара для всех]");
        while (true) {
            System.out.println("Введите данные продуктов." + "\n" + "Для выхода напишите END");
            String rawProduct = scanner.nextLine();
            String[] productAttrs = rawProduct.split(" = ");
            if (Objects.equals(rawProduct, "END")) {
                break;
            } else if (!rawProduct.contains(String.valueOf('%'))) {
                Product product = Product.of(productAttrs);
                if (product != null) {
                    products.add(product);
                }
            } else {
                DiscountProduct discountProduct = DiscountProduct.of(productAttrs);
                if (discountProduct != null) {
                    discountProducts.add(discountProduct);
                }
            }
        }
    }

    //    Печать названия всех продуктов обычных и акционных
    private static void printAllProducts(List<? extends Product> products, String productNamePrefix) {
        System.out.print(productNamePrefix + ": ");
        System.out.println(products.stream()
                .map(Product::getProductName)
                .collect(Collectors.joining(", ")));
    }

    private static Map<String, Person> enterPersons() {
        Map<String, Person> mapPerson = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите данные покупателя в виде: \n[Имя покупателя], " +
                "[Кол-во денег у покупателя], [Возраст]");
        while (true) {
            System.out.println("Введите данные покупателя." + "\n" + "Для выхода напишите END");
            String list = scanner.nextLine();
            if (Objects.equals(list, "END")) {
                break;
            } else {
                String[] listPerson = list.split(", ");
                String name = listPerson[0];
                int amount = Integer.parseInt(listPerson[1]);
                int agePerson = Integer.parseInt(listPerson[2]);
                if (validate(name, amount, agePerson)) {
                    if (agePerson < 18) {
                        Person person = new Kid(name, amount, agePerson);
                        mapPerson.put(name, person);
                    } else if (agePerson < 65) {
                        Person person = new Adult(name, amount, agePerson);
                        mapPerson.put(name, person);
                    } else {
                        Person person = new Retiree(name, amount, agePerson);
                        mapPerson.put(name, person);
                    }
                }

            }
        }
        return mapPerson;
    }

    private static void inputPersonAndProduct(Map<String, Person> personMap, List<Product> products,
                                              List<DiscountProduct> discountProducts) {
        System.out.println("\nВведите данные о покупке в формате:");
        System.out.println("[Имя покупателя]-[Имя продукта]");
        while (true) {
            System.out.println("Введите данные о покупке." + "\n" + "Для выхода напишите END");
            Scanner sc = new Scanner(System.in);
            String scanner = sc.nextLine();

            if (Objects.equals(scanner, "END")) {
                break;
            } else {
                String personName = scanner.substring(0, scanner.indexOf("-"));
                String productName = scanner.substring(scanner.indexOf("-") + 1);
                Product product = searchProduct(productName, products, discountProducts);
                Person personFromMap = personMap.get(personName);
                if (product == null) {
                    System.out.println("Такого продукта нет в списке!");
                } else {
                    if (personMap.containsKey(personName)) {

                        if (personFromMap.getAge() < 18) {
                            Kid kid = (Kid) personFromMap;
                            if (kid.tryBuyProduct(product)) {
                                System.out.println(kid.getName() + " купил " + product.getProductName());
                            } else {
                                System.out.println(kid.getName() + " не может купить " + product.getProductName());
                            }

                        } else if (personFromMap.getAge() >= 18 && personFromMap.getAge() < 65) {
                            Adult adult = (Adult) personFromMap;
                            if (adult.tryBuyProduct(product)) {
                                System.out.println(adult.getName() + " купил " + product.getProductName());
                            } else {
                                System.out.println(adult.getName() + " не может купить " + product.getProductName());
                            }

                        } else if (personFromMap.getAge() >= 65) {
                            Retiree retiree = (Retiree) personFromMap;
                            if (retiree.tryBuyProduct(product)) {
                                System.out.println(retiree.getName() + " купил " + product.getProductName());
                            } else {
                                System.out.println(retiree.getName() + " не может купить " + product.getProductName());
                            }
                        }
                    } else {
                        System.out.println("Такого имени нет в списке!");
                    }
                }
            }
        }
    }

    public static void printFinal(Map<String, Person> personMap) {
        for (Map.Entry<String, Person> personEntry : personMap.entrySet()) {
            Person person = personEntry.getValue();
            System.out.println(person.getName() + " купил: " + person.getProductPackage());
        }
    }

    private static Product searchProduct(String productName, List<Product> products, List<DiscountProduct> discountProducts) {
        for (Product product : products) {
            if (Objects.equals(product.getProductName(), productName)) {
                return product;
            }
        }

        for (DiscountProduct discountProduct : discountProducts) {
            if (Objects.equals(discountProduct.getProductName(), productName)) {
                return discountProduct;
            }
        }
        return null;
    }
}
