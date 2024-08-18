package ru.innopolis.java.homeworks.homework07;

import java.time.LocalDate;
import java.util.Objects;

public class DiscountProduct extends Product {

    private int discount;
    private final LocalDate endDiscountTime;

    public DiscountProduct(String productName, int productPrice, int discount, LocalDate discountTime) {
        super(productName, productPrice);
        this.discount = discount;
        this.endDiscountTime = discountTime.plusDays(timeOfAction(discount));
    }

    //    Из массивов строк, полученный с консоли, создаем объект класса DiscountProduct
    public static DiscountProduct of(String[] value) {
        String name = value[0];
        String[] priceAndDiscount = value[1].split(", ");
        int productPrice = Integer.parseInt(priceAndDiscount[0]);
        int discount = Integer.parseInt(priceAndDiscount[1].substring(0,
                priceAndDiscount[1].lastIndexOf("%")));
        LocalDate timeDiscount = LocalDate.now();
        if (validateDiscountProduct(name, productPrice, discount)) {
            return new DiscountProduct(name, productPrice, discount, timeDiscount);
        }
        return null;
    }

    //    Проверяем продукт на правильное имя, цену и скидку
    public static boolean validateDiscountProduct(String name, int price, int discount) {
        if (name.length() < 3) {
            System.out.println("Недопустимое имя продукта!");
            return false;
        }
        if (name.matches("\\d+")) {
            System.out.println("Недопустимое имя продукта!");
            return false;
        }
        if (price <= 0) {
            System.out.println("Недопустимая стоимость продукта!");
            return false;
        }
        if (discount < 3 || discount > 50) {
            System.out.println("Недопустиме значение скидки!\nСкидка не меньше 3% и не более 50%");
            return false;
        }
        return true;
    }

    public boolean valueDiscount(Integer value) {
        return 3 < value && value > 50;
    }

    //    время действия скидки, в зависимости от размера скидки
    private int timeOfAction(Integer discount) {
        if (discount <= 15 && discount >= 3) {
            return 7;
        } else if (discount > 15 && discount <= 30) {
            return 5;
        } else return 2;
    }

    //    проверка срока действия скидки
    private Integer checkingDiscountForTime(LocalDate endDiscountTime, Integer discount) {
        if (endDiscountTime.isBefore(LocalDate.now())) {
            if (discount <= 15 && discount >= 3) {
                return (discount + 7);
            } else if (discount > 15 && discount <= 30) {
                return (discount + 5);
            } else return (discount + 3);
        } else return discount;
    }

    public int getDiscount() {
        return discount = checkingDiscountForTime(getDiscountTime(), discount);
    }

    public LocalDate getDiscountTime() {
        return endDiscountTime;
    }

    @Override
    public String toString() {
        return "DiscountProduct{" +
                "endDiscountTime=" + endDiscountTime +
                ", discount=" + discount +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountProduct that)) return false;
        if (!super.equals(o)) return false;
        return discount == that.discount && Objects.equals(endDiscountTime, that.endDiscountTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount, endDiscountTime);
    }
}
