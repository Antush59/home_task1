package ru.innopolis.java.homeworks.homework07Addition;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Objects;

public class Product implements Checking {

    private final String productName;
    private final Integer productPrice;
    private Boolean adultProduct;

    public Product(String productName, Integer productPrice, Boolean adultProduct) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.adultProduct = adultProduct;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public Boolean getAdultProduct() {
        return adultProduct;
    }

    @Override
    public boolean checkProductName(String value) {
        return value.length() < 3;
    }

    public boolean checkProductNameInt(String value) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(value, pos);
        return value.length() == pos.getIndex();
    }

    @Override
    public boolean checkPrice(Integer price) {
        return price <= 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice + ", " +
                checkAdultProduct(getAdultProduct()) +
                '}';
    }

    public String checkAdultProduct (Boolean adultProduct) {
        if (adultProduct) {
            return "Товара не предназначен для детей!";
        } else return "Товар подходит для детей!";
    }

    //    Проверяем продукт на правильное имя и цену
    public boolean check(){
        if (checkProductNameInt(getProductName())) {
            System.out.println("Недопустимое имя продукта!");
        } else if (checkProductName(getProductName())) {
            System.out.println("Недопустимое имя продукта!");
        } else if (checkPrice(getProductPrice())) {
            System.out.println("Недопустимая стоимость продукта!");
        } else {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getProductName(), product.getProductName()) &&
                Objects.equals(getProductPrice(), product.getProductPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getProductPrice());
    }
}
