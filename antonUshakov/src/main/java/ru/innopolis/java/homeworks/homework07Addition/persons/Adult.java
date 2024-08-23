package ru.innopolis.java.homeworks.homework07Addition.persons;

import ru.innopolis.java.homeworks.homework07Addition.products.Product;

public class Adult extends Person {

    private boolean creditIsRegistered;

    public Adult(String name, int amountOfMoney, int age) {
        super(name, amountOfMoney, age);
        this.creditIsRegistered = creditIsAllowed();
    }

    @Override
    public boolean tryBuyProduct(Product product) {
        if (getAmountOfMoney() < product.getProductPrice() && creditIsRegistered) {
            System.out.println("""
                    Не хватает денег!
                    Но можно взять кредит""");
            return false;
        } else if (getAmountOfMoney() < product.getProductPrice() && !creditIsRegistered) {
            System.out.println("""
                    Не хватает денег!
                    Кредит взять нельзя""");
            return false;
        } else if (getAmountOfMoney() >= product.getProductPrice()) {
            amountOfMoney = getAmountOfMoney() - product.getProductPrice();
            addProductToPacket(product.getProductName());
            return true;
        }
        return false;
    }

    private boolean creditIsAllowed() {
        return getAge() > 20 && getAge() < 45;
    }

}
