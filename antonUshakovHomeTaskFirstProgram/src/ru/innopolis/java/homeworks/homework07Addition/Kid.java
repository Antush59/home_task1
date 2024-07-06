package ru.innopolis.java.homeworks.homework07Addition;

import java.util.List;

public class Kid extends Person{

    public Kid(String name, Integer amountOfMoney, List<String> productPackage, Integer age) {
        super(name, amountOfMoney, productPackage, age);
    }

    public boolean checkingAge(Integer ageKid) {
        return ageKid >= 6;
    }

    public void checkingAmountOfMoney(Product product) {
        if (!product.getAdultProduct()) {

        }
    }
}
