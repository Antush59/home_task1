package ru.innopolis.java.homeworks.homework07Addition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class Person {

    protected final String name;
    protected Integer amountOfMoney;
    protected List<String> productPackage = new ArrayList<>();
    protected Integer age;

    public Person(String name, Integer amountOfMoney, List<String> productPackage, Integer age) {
        this.name = name;
        this.amountOfMoney = amountOfMoney;
        this.productPackage = productPackage;
        this.age = age;
    }

    public void checkingAmountOfMoney(Product product) {

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

    public Integer getAge() {
        return age;
    }

    public void productPacket(String productName) {
        productPackage.add(productName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getName(), person.getName()) && Objects.equals(getAge(), person.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", amountOfMoney=" + amountOfMoney +
                ", productPackage=" + productPackage +
                ", age=" + age +
                '}';
    }
}
/*
Person p = map.get("name");
if (p instance of Kid.class) {
    ((Kid) p).tryBuyProduct(....)
} else if (p in
 */
