package ru.innopolis.java.homeworks.homework07Addition;

import java.time.LocalDate;
import java.util.Objects;

public class DiscountProduct extends Product {

    private Integer discount;
    private final LocalDate endDiscountTime;

    public DiscountProduct(String productName, Integer productPrice, Integer discount,
                           LocalDate discountTime, Boolean adultProduct) {
        super(productName, productPrice, adultProduct);
        this.discount = discount;
        this.endDiscountTime = discountTime.plusDays(timeOfAction(discount));
    }

    public boolean valueDiscount(Integer value) {
        return 3 < value && value > 50;
    }

//    время действия скидки, в зависимости от размера скидки
    private Integer timeOfAction(Integer discount) {
        if (discount <= 15 && discount >= 3) {
            return 7;
        } else if (discount > 15 && discount <=30) {
            return 5;
        }else return 2;
    }

//    проверка срока действия скидки и изменения скидки, если срок закончился
    private Integer checkingDiscountForTime(LocalDate endDiscountTime, Integer discount) {
        if (endDiscountTime.isBefore(LocalDate.now())) {
            if (discount <= 15 && discount >= 3) {
                return (discount + 7);
            } else if (discount > 15 && discount <=30) {
                return (discount + 5);
            }else {
                return (discount + 3);
            }
        } else return discount;
    }

//    цена продукта после приминения скидки
    public Integer getPriceAfterDiscount(Integer price, Integer discount) {
        return price * (discount / 100);
    }

    //    Проверяем продукт на правильное имя, цену и скидку
    public boolean check (){
        if (checkProductNameInt(getProductName())) {
            System.out.println("Недопустимое имя продукта!");
        } else if (checkProductName(getProductName())) {
            System.out.println("Недопустимое имя продукта!");
        } else if (checkPrice(getProductPrice())) {
            System.out.println("Недопустимая стоимость продукта!");
        } else if (valueDiscount(getDiscount())) {
            System.out.println("Недопустиме значение скидки!\nСкидка не меньше 3% и не более 50%");
        } else {
            return true;
        }
        return false;
    }

    public Integer getDiscount() {
        return discount = checkingDiscountForTime(getDiscountTime(), discount);
    }

    public LocalDate getDiscountTime() {
        return endDiscountTime;
    }

    @Override
    public String toString() {
        return "DiscountProduct{" +
                ", discount=" + discount +
                ", endDiscountTime=" + endDiscountTime +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountProduct that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDiscount(), that.getDiscount()) && Objects.equals(getDiscountTime(),
                that.getDiscountTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDiscount(), getDiscountTime());
    }
}
