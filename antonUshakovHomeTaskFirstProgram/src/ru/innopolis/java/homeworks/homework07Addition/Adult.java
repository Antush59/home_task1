package ru.innopolis.java.homeworks.homework07Addition;

import java.util.List;
import java.util.Scanner;

public class Adult extends Person{

    private Integer creditAmount;

    public Adult(String name, Integer amountOfMoney, List<String> productPackage, Integer age) {
        super(name, amountOfMoney, productPackage, age);
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public void checkingAmountOfMoney(Product product) {
        if (this.amountOfMoney < product.getProductPrice()) {
            System.out.println("Ваших средств не достаточно! \n" +
                    "Доступен кредит от 1000 до 10000 рублей\n" +
                    "Введите необходимую сумму или END, если не хотите брать кредит:");
            Scanner scanner = new Scanner(System.in);
            if (scanner.equals("END")) {
            } else {
                creditAmount = Integer.parseInt(String.valueOf(scanner));
                this.amountOfMoney = amountOfMoney + creditAmount;
            }
        }
    }


}
