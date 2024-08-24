package ru.innopolis.java.homeworks.homework08.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person {

    private final String name;
    private Integer amountOfMoney;
    private List<Product> productPackage = new ArrayList<>();

    public Person(String name, Integer amountOfMoney, List<Product> productPackage) {
        this.name = name;
        this.amountOfMoney = amountOfMoney;
        this.productPackage = productPackage;
    }

    public Person(String[] value) {
        name = value[0];
        amountOfMoney = Integer.parseInt(value[1]);
    }

    public String getName() {
        return name;
    }

    public Integer getAmountOfMoney() {
        return amountOfMoney;
    }

    private void setAmountOfMoney(Integer amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public List<Product> getProductPackage() {
        return productPackage;
    }

    public boolean addProductToPackage(Product product) {
        if (getAmountOfMoney() < product.getPrice()) {
            return false;
        } else {
            setAmountOfMoney(getAmountOfMoney() - product.getPrice());
            productPackage.add(product);
            return true;
        }
    }

    private String getProductNameOfProductPackage(List<Product> products) {
        if (products.isEmpty()) {
            return "Ничего не куплено!";
        } else {
            return products.stream()
                    .map(Product::getProductName)
                    .collect(Collectors.joining(", "));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(name, person.name) && Objects.equals(amountOfMoney, person.amountOfMoney)
                && Objects.equals(productPackage, person.productPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amountOfMoney, productPackage);
    }

    @Override
    public String toString() {
        return getName() + " - " + getProductNameOfProductPackage(productPackage);
    }
}
