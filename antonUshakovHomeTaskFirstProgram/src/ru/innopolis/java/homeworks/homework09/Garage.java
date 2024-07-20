package ru.innopolis.java.homeworks.homework09;

import ru.innopolis.java.homeworks.homework09.cars.Car;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Garage {

    private List<Car> garageCarList;

    public Garage(List<Car> garageCarList) {
        this.garageCarList = garageCarList;
    }

    public void upgradeCar () {
        Scanner scanner = new Scanner(System.in);
        String value;
        for (Car car : garageCarList) {

            System.out.println("Мощность автомобиля составляет: " + car.getEnginePower());
            System.out.println("На сколько изменить мощность двигателя?\nЕсли не нужно напишите NO!");
            value = scanner.nextLine();
            if (!value.equals("NO")) {
                car.setEnginePower(car.getEnginePower() + Integer.parseInt(value));
            } else break;

            System.out.println(car.getEnginePower()); //testing

            System.out.println("Ускорение автомобиля составляет: " + car.getSpeedup());
            System.out.println("На сколько изменить ускорение двигателя?\nЕсли не нужно напишите NO!");
            value = scanner.nextLine();
            if (!value.equals("NO")) {
                car.setSpeedup(car.getSpeedup() + Integer.parseInt(value));;
            } else break;

            System.out.println(car.getSpeedup()); //testing

            System.out.println("Клиренс автомобиля составляет: " + car.getSpeedup());
            System.out.println("На сколько изменить клиренс двигателя?\nЕсли не нужно напишите NO!");
            value = scanner.nextLine();
            if (!value.equals("NO")) {
                car.setClearance(car.getClearance() + Integer.parseInt(value));
            } else break;

            System.out.println(car.getClearance()); //testing
        }
    }
}
