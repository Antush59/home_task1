package ru.innopolis.java.homeworks.homework07;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Objects;

public class Product implements Checking{

    private final String productName;
    private final Integer productPrice;


    public Product(String productName, Integer productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductPrice() {
        return productPrice;
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
                ", productPrice=" + productPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getProductPrice(), product.getProductPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getProductPrice());
    }
}
