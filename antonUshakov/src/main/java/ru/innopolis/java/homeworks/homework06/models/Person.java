package ru.innopolis.java.homeworks.homework06.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private Integer amountOfMoney;
    private List<Product> productPackage = new ArrayList<>();

    public Person(String name, Integer amountOfMoney) {
        this.name = name;
        this.amountOfMoney = amountOfMoney;
    }

    public Person(String[] value) throws NumberFormatException {
        name = value[0];
        try {
            amountOfMoney = Integer.parseInt(value[1]);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Не верный формат наличных");
        }
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public Integer getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Integer amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public List<Product> getProductPackage() {
        return productPackage;
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
        return Objects.equals(name, person.name) && Objects.equals(amountOfMoney, person.amountOfMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amountOfMoney);
    }

    @Override
    public String toString() {
        return getName() + " - " + getProductNameOfProductPackage(productPackage);
    }
}
