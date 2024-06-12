package ru.innopolis.java.homeTask.task3;

import java.util.Scanner;

public class Television {

    private String companyName;
    private boolean powerButton;
    private int diagonalSize;
    private int numberChanel;

    public Television(String companyName, int diagonalSize) {
        this.companyName = companyName;
        this.diagonalSize = diagonalSize;
    }

    //Включение телевизора.
    public void pushPowerButton(int pwButton) {
        if(pwButton == 1) {
            powerButton = true;
            System.out.println("Телевизор включен");
        } else {
            powerButton = false;
            System.out.println("Телевизор выключен");
        }
    }

    //Выбор канала от 1 до 400. Исключен выбор канала, если телевизор выключен.
    //Исключен выбор канала больше 400.
    public void choiceNumberChanel () {
        Scanner scanner = new Scanner(System.in);
        numberChanel = scanner.nextInt();
        if (powerButton == true) {
            if (numberChanel <= 400) {
                System.out.println("Включен " + numberChanel + " канал");
            } else {
                System.out.println("Выберите канал от 1 до 400");
                choiceNumberChanel();
            }
        } else {
            System.out.println("Телевизор не включен");
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public boolean isPowerButton() {
        return powerButton;
    }

    public int getDiagonalSize() {
        return diagonalSize;
    }

    public void setPowerButton(boolean powerButton) {
        this.powerButton = powerButton;
    }

    public void setDiagonalSize(int diagonalSize) {
        this.diagonalSize = diagonalSize;
    }

    @Override
    public String toString() {
        return "Television{" +
                "companyName='" + companyName + '\'' +
                ", diagonalSize=" + diagonalSize +
                '}';
    }
}
