package ru.innopolis.java.homeworks.homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    private final String name;
    private Integer amountOfMoney;
    private List<String> productPackage = new ArrayList<>();

    public Person(String name, Integer amountOfMoney, List<String> productPackage) {
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

    public void setAmountOfMoney(Integer amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public List<String> getProductPackage() {
        return productPackage;
    }

    public void productPacket(String productName) {
        productPackage.add(productName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getName(), person.getName()) && Objects.equals(getAmountOfMoney(),
                person.getAmountOfMoney()) && Objects.equals(getProductPackage(), person.getProductPackage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAmountOfMoney(), getProductPackage());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", amountOfMoney=" + amountOfMoney +
                ", productPackage=" + productPackage +
                '}';
    }

//    //    проверяем, что имя должно быть у покупателя
//    public static boolean checkName(String name) {
//        return name != "";
//    }
//
//    //    Проверяем положительнцю сумму у покупателя
//    public static boolean checkMoney(Integer money) {
//        return money >= 0;
//    }
}
