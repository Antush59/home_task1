package ru.innopolis.java.homeworks.homework07;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;


public class App {

    public static void main(String[] args) {

//        Создаем массивы данных классов Product и DiscountProduct
        List<Product> products = new ArrayList<>();
        List<DiscountProduct> discountProducts = new ArrayList<>();

        enteringProducts(products, discountProducts);

//        Проверяем пусто или нет массив, если нет, то выполняем метод printAll
        if (products.isEmpty()) {
            System.out.println("Обычные продукты не внесены.");
        } else {
            printAllProducts(products, "Обычные продукты");
        }

        if (discountProducts.isEmpty()) {
            System.out.println("Акционные продукты не внесены.");
        } else {
            printAllProducts(discountProducts, "Акционные продукты");
        }
    }

//        Вводим с консоли данные о продуктах
//        Если с консоли введо "END", то цикл прерывается.
//        Для ввода данных используется формат [Название продукта] = [Цена продукта],
//        [если есть, то скидка продукта]
    public static void enteringProducts(List<Product> products, List<DiscountProduct> discountProducts) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные продуктов в формате: \n" + "[Имя продукта] = [Цена продукта], " +
                "[Скидка товара, если есть]");
        while (true) {
            System.out.println("Введите данные");
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
}
