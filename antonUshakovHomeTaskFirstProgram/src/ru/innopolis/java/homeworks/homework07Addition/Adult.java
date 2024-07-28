package ru.innopolis.java.homeworks.homework07Addition;

import java.util.Scanner;

public class Adult extends Person {

    private int creditAmount;

    public Adult(String name, int amountOfMoney, int age) {
        super(name, amountOfMoney, age);
    }

    private int creditMoney() {
        while (true) {
            System.out.println("Ваших средств не достаточно! \n" +
                    "Доступен кредит от 1000 до 10000 рублей\n" +
                    "Введите необходимую сумму или END, если не хотите брать кредит:");
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            int value = Integer.parseInt(string);
            if (value < 1000 || value > 10000) {
                System.out.println("Кредит не может быть выбран ниже 1000  и выше 10000!");
            } else if (string.equals("END")) {
                creditAmount = 0;
                break;
            } else {
                creditAmount = value;
                break;
            }
        }
        return creditAmount;
    }

    @Override
    public boolean tryBuyProduct(Product product) {
        if (amountOfMoney < product.getProductPrice()) {
            amountOfMoney = (amountOfMoney + creditMoney()) - product.getProductPrice();
            if (amountOfMoney < 0) {
                System.out.println("Кредитных денег не достаточно!");
                return false;
            } else {
                addProductToPacket(product.getProductName());
                return true;
            }
        } else {
            amountOfMoney = amountOfMoney - product.getProductPrice();
            addProductToPacket(product.getProductName());
            return true;
        }
    }


}
