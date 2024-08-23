package ru.innopolis.java.homeworks.homework07Addition.persons;

import ru.innopolis.java.homeworks.homework07Addition.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    protected final String name;
    protected int amountOfMoney;
    protected final List<String> productPackage;
    protected final int age;

    public Person(String name, int amountOfMoney, int age) {
        this.name = name;
        this.amountOfMoney = amountOfMoney;
        this.productPackage = new ArrayList<>();
        this.age = age;
    }

    public boolean tryBuyProduct(Product product) {
        return true;
    }

    public String getName() {
        return name;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public List<String> getProductPackage() {
        return productPackage;
    }

    public int getAge() {
        return age;
    }

    public void addProductToPacket(String productName) {
        productPackage.add(productName);
    }

    public static boolean validate(String name, int amount, int agePerson) {
        if (Objects.equals(name, "")) {
            System.out.println("Имя не может быть пустой строкой!");
            return false;
        } else if (amount < 0) {
            System.out.println("Деньги не могут быть отрицательными!");
            return false;
        } else if (agePerson < 0) {
            System.out.println("Возраст не может быть отрицательным!");
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person person)) {
            return false;
        }
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
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

