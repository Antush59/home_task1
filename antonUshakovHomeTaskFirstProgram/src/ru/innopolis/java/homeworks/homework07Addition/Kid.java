package ru.innopolis.java.homeworks.homework07Addition;

public class Kid extends Person {

    public Kid(String name, Integer amountOfMoney, Integer age) {
        super(name, amountOfMoney, age);
    }

//    Проверка ребенка на возраст для возможности покупать продукеты
//    покупать можно с 6 лет
    public boolean checkingAge() {
        return getAge() >= 6;
    }

    @Override
    public boolean tryBuyProduct(Product product) {
        if (product.getAdultProduct()) {
            System.out.println("Товар не предназначен для детей!");
            return false;
        } else if (!checkingAge()) {
            System.out.println("Ребенок моложе 6 лет не может покупать товары!");
            return false;
        } else if (getAmountOfMoney() < product.getProductPrice()) {
            System.out.println("Не хватает денег!");
            return false;
        } else if (getAmountOfMoney() >= product.getProductPrice()) {
            amountOfMoney = getAmountOfMoney() - product.getProductPrice();
            addProductToPacket(product.getProductName());
            return true;
        }
        return false;
    }
}

