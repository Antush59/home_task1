package ru.innopolis.java.homeworks.homework07;

import java.util.Objects;

public class Product {

    private final String productName;
    private final int productPrice;


    public Product(String productName, int productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    //    Из массивов строк, полученный с консоли, создаем объект класса Product
    public static Product of(String[] value) {
        String name = value[0];
        Integer price = Integer.parseInt(value[1]);
        if (validateProduct(name, price)) {
            return new Product(name, price);
        }
        return null;
    }

    //    Проверяем продукт на правильное имя и цену
    private static boolean validateProduct(String name, int price) {
        if (name.length() < 3) {
            System.out.println("Недопустимое имя продукта!");
            return false;
        }
        if (name.matches("\\d+")) {
            System.out.println("Недопустимое имя продукта!");
            return false;
        }
        if (price <= 0) {
            System.out.println("Недопустимая стоимость продукта!");
            return false;
        }
        return true;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return productPrice == product.productPrice && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice);
    }
}
