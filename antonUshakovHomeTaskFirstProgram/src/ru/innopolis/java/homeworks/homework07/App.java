package ru.innopolis.java.homeworks.homework07;

import java.time.LocalDate;
import java.util.*;

/**
 * Нужно сделать проверку после добавления
 */
public class App {

    public static void main(String[] args) {

//        Создаем массивы данных классов Product и DiscountProduct
        List<Product> products = new ArrayList<>();
        List<DiscountProduct> discountProducts = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

//        Вводим с консоли данные о продуктах
//        Если с консоли введо "END", то цикл прерывается.
//        Для ввода данных используется формат [Название продукта] = [Цена продукта],
//        [если есть, то скидкапродукта]
        while (true) {
            System.out.println("Введите данные");
            String list = scanner.nextLine();
            String[] listProduct = list.split(" = ");
            if (Objects.equals(list, "END")) {
                break;
            } else if (list.lastIndexOf("%") == -1) {
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

//        Проверяем пусто или нет массив, если нет, то выполняем метод printAll
        if (products.isEmpty()){
            System.out.println("Продукты не внесены.");
        } else if (discountProducts.isEmpty()){
        } else {
            printAll(products, discountProducts);
        }
    }

//    Из массивов строк, полученный с консоли, создаем объект класса Product
    public static Product productParse(String[] value) {
        String name = value[0];
        Integer price = Integer.parseInt(value[1]);
        return new Product(name, price);
    }

//    Проверяем продукт на правильное имя и цену
    public static boolean checkProduct(Product product) {
        if (product.checkProductNameInt(product.getProductName())) {
            System.out.println("Недопустимое имя продукта!");
        } else if (product.checkProductName(product.getProductName())) {
            System.out.println("Недопустимое имя продукта!");
        } else if (product.checkPrice(product.getProductPrice())) {
            System.out.println("Недопустимая стоимость продукта!");
        } else {
            return true;
        }
        return false;
    }

//    Из массивов строк, полученный с консоли, создаем объект класса DiscountProduct
    public static DiscountProduct discountParse(String[] value) {
        String name = value[0];
        String[] priceAndDiscount = value[1].split(", ");
        Integer price = Integer.parseInt(priceAndDiscount[0]);
        Integer discount = Integer.parseInt(priceAndDiscount[1].substring(0,
                priceAndDiscount[1].lastIndexOf("%")));
        LocalDate timeDiscount = LocalDate.now();
        return new DiscountProduct(name, price, discount, timeDiscount);
    }

//    Проверяем продукт на правильное имя, цену и скидку
    public static boolean checkProductDiscount(DiscountProduct productDiscount) {
        if (productDiscount.checkProductNameInt(productDiscount.getProductName())) {
            System.out.println("Недопустимое имя продукта!");
        } else if (productDiscount.checkProductName(productDiscount.getProductName())) {
            System.out.println("Недопустимое имя продукта!");
        } else if (productDiscount.checkPrice(productDiscount.getProductPrice())) {
            System.out.println("Недопустимая стоимость продукта!");
        } else if (productDiscount.valueDiscount(productDiscount.getDiscount())) {
            System.out.println("Недопустиме значение скидки!\nСкидка не меньше 3% и не более 50%");
        } else {
            return true;
        }
        return false;
    }

//    Печать названия всех продуктов обычных и акционных
    public static void printAll(List<Product> products, List<DiscountProduct> discountProducts) {
        int count = 0;
        String[] listNameProduct = new String[products.size()];
        for (Product product : products) {
            listNameProduct[count] = product.getProductName();
            count++;
        }
        System.out.print("Обычные продукты: ");
        System.out.println(Arrays.toString(listNameProduct)
                .replace("[", "").replace("]", ""));

        count = 0;
        String[] listDiscountProduct = new String[discountProducts.size()];
        for (DiscountProduct discountProduct : discountProducts) {
            listDiscountProduct[count] = discountProduct.getProductName();
            count ++;
        }
        System.out.print("\nАкционные продукты: ");
        System.out.println(Arrays.toString(listDiscountProduct)
                .replace("[", "").replace("]", ""));


    }
}
