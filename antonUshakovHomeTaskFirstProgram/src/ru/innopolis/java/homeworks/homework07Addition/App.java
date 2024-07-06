package ru.innopolis.java.homeworks.homework07Addition;

import java.time.LocalDate;
import java.util.*;


public class App {

    public static void main(String[] args) {

//        Создаем массивы данных классов Product и DiscountProduct
        List<Product> products = new ArrayList<>();
        List<DiscountProduct> discountProducts = new ArrayList<>();
        List<Kid> kids = new ArrayList<>();
        List<Adult> adults = new ArrayList<>();
        List<Retiree> retirees = new ArrayList<>();
        List<String> productPackage = new ArrayList<>();

        enteringProducts(products, discountProducts);

//        Проверяем пусто или нет массив, если нет, то выполняем метод printAll
        if (products.isEmpty()) {
            System.out.println("Продукты не внесены.");
        } else {
            printAllProducts(products);
        }

        if (discountProducts.isEmpty()) {
            System.out.println("Продукты не внесены.");
        } else {
            printAllDiscountProducts(discountProducts);
        }

        enteringPerson(kids, adults, retirees, productPackage);

    }

//        Вводим с консоли данные о продуктах
//        Если с консоли введено "END", то цикл прерывается.
//        Для ввода данных используется формат [Название продукта] = [Цена продукта],
//        [если есть, то скидка продукта]
    public static void enteringProducts(List<Product> products, List<DiscountProduct> discountProducts) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите данные");
            String list = scanner.nextLine();
            String[] listProduct = list.split(" = ");
            if (Objects.equals(list, "END")) {
                break;
            } else if (!list.contains(String.valueOf('%'))) {
                Product product = productParse(listProduct);
                if (checkProduct(product)) {
                    products.add(product);
                }
            } else {
                DiscountProduct discountProduct = discountParse(listProduct);
                if (checkProductDiscount(discountProduct)) {
                    discountProducts.add(discountProduct);
                }
            }
        }
    }

    private static void enteringPerson(List<Kid> kids, List<Adult> adults,
                                       List<Retiree> retirees, List<String> productPackage) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите данные покупателя в виде: \n[Имя покупателя], " +
                    "[Кол-во денег у покупателя], [Возраст]\n" +
                    "Для выхода напишите END");
            String list = scanner.nextLine();
            String age = list.substring(list.lastIndexOf(", ")).replace(", ", "");
            String[] listPerson = list.split(", ");
            String name = listPerson[0];
            Integer amount = Integer.parseInt(listPerson[1]);
            Integer agePerson = Integer.parseInt(listPerson[2]);
            if (Objects.equals(list, "END")) {
                break;
            } else if (Integer.parseInt(age) < 18) {
                Kid kid = new Kid(name, amount, productPackage, agePerson);
                if (kid.checkingAge(agePerson)) {
                    kids.add(kid);
                } else {
                    System.out.println("Ребенок моложе 6 лет не может покупать продукты!");
                }
            } else if (Integer.parseInt(age) > 17 && Integer.parseInt(age) < 65) {
                Adult adult = new Adult(name, amount, productPackage, agePerson);
                adults.add(adult);
            } else if (Integer.parseInt(age) >= 65) {
                Retiree retiree = new Retiree(name, amount, productPackage, agePerson);
                retirees.add(retiree);
            }
        }
    }

    //    Из массивов строк, полученный с консоли, создаем объект класса Product
    public static Product productParse(String[] value) {
        String name = value[0];
        String[] listPars = value[1].split(", ");
        Integer price = Integer.parseInt(listPars[0]);
        Boolean adultProduct = Boolean.parseBoolean(listPars[1]);
        return new Product(name, price, adultProduct);
    }

    //    Проверяем продукт на правильное имя и цену
    public static boolean checkProduct(Product product) {
        return product.check();
    }

    //    Из массивов строк, полученный с консоли, создаем объект класса DiscountProduct
    public static DiscountProduct discountParse(String[] value) {
        String name = value[0];
        String[] infoDiscount = value[1].split(", ");
        Integer price = Integer.parseInt(infoDiscount[0]);
        Integer discount = Integer.parseInt(infoDiscount[1].substring(0,
                infoDiscount[1].lastIndexOf("%")));
        Boolean adultProduct = Boolean.parseBoolean(infoDiscount[2]);
        LocalDate timeDiscount = LocalDate.now();
        return new DiscountProduct(name, price, discount, timeDiscount, adultProduct);
    }

    //    Проверяем продукт на правильное имя, цену и скидку
    public static boolean checkProductDiscount(DiscountProduct productDiscount) {
        return productDiscount.check();
    }

    //    Печать названия всех продуктов обычных и акционных
    public static void printAllProducts(List<Product> products) {
        int count = 0;
        String[] listNameProduct = new String[products.size()];
        for (Product product : products) {
            listNameProduct[count] = product.getProductName();
            count++;
        }
        System.out.print("Обычные продукты: ");
        System.out.println(Arrays.toString(listNameProduct)
                .replace("[", "").replace("]", ""));
    }

    public static void printAllDiscountProducts(List<DiscountProduct> discountProducts) {
        int count = 0;
        String[] listDiscountProduct = new String[discountProducts.size()];
        for (DiscountProduct discountProduct : discountProducts) {
            listDiscountProduct[count] = discountProduct.getProductName();
            count++;
        }
        System.out.print("\nАкционные продукты: ");
        System.out.println(Arrays.toString(listDiscountProduct).replace("[", "")
                .replace("]", ""));
    }
}
