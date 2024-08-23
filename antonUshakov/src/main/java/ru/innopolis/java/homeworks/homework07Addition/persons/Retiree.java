package ru.innopolis.java.homeworks.homework07Addition.persons;

import ru.innopolis.java.homeworks.homework07Addition.products.DiscountProduct;
import ru.innopolis.java.homeworks.homework07Addition.products.Product;

public class Retiree extends Person {

    public Retiree(String name, int amountOfMoney, int age) {
        super(name, amountOfMoney, age);
    }

    @Override
    public boolean tryBuyProduct(Product product) {
        if (product instanceof DiscountProduct) {
            if (getAmountOfMoney() >= product.getProductPrice()) {
                amountOfMoney = amountOfMoney - (product.getProductPrice() - (product.getProductPrice() * 5 / 100));
                addProductToPacket(product.getProductName());
                return true;
            }
        } else {
            System.out.println(name + " не покупает обычные продукты");
        }
        return false;
    }

}
