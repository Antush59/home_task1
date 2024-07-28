package ru.innopolis.java.homeworks.homework07Addition;

import java.util.Objects;

public class Product {

    protected final String productName;
    protected final int productPrice;
    protected final boolean adultProduct;

    public Product(String productName, int productPrice, boolean adultProduct) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.adultProduct = adultProduct;
    }

    public static Product of(String[] value) {
        String name = value[0];
        String[] attributes = value[1].split(", ");
        int price = Integer.parseInt(attributes[0]);
        boolean adultProduct = Boolean.parseBoolean(attributes[1]);
        if(validateProduct(name, price)) {
            return new Product(name, price, adultProduct);
        }
        return null;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public boolean getAdultProduct() {
        return adultProduct;
    }

    private String checkAdultProduct (Boolean adultProduct) {
        if (adultProduct) {
            return "Товара не предназначен для детей!";
        } else return "Товар подходит для детей!";
    }

    //    Проверяем продукт на правильное имя и цену
    public static boolean validateProduct(String name, int price) {
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

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice + ", " +
                checkAdultProduct(getAdultProduct()) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return productPrice == product.productPrice && adultProduct == product.adultProduct && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice, adultProduct);
    }
}
