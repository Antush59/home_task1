package ru.innopolis.java.homeTask.task3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Television television1 = new Television("SAMSUNG", 105);
        Television television2 = new Television("SONY", 115);

        usingTV(television1);

        System.out.println();

        usingTV(television2);
    }

//Создали метод для реализации работы с телевизором
    private static void usingTV(Television television) {
        Scanner scanner = new Scanner(System.in);
        int powerButton;

        System.out.println(television.toString());

        System.out.println("Для включения телевизора напишите 1");
        powerButton = scanner.nextInt();
        television.pushPowerButton(powerButton);

        System.out.println("Выберите канал");
        television.choiceNumberChanel();
    }
}
