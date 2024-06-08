package ru.innopolis.java.homeTask.task3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        TV tv1 = new TV("SAMSUNG", 105);
        TV tv2 = new TV("SONY", 115);

        usingTV(tv1);

        System.out.println();

        usingTV(tv2);
    }

//Создали метод для реализации работы с телевизором
    private static void usingTV(TV tv) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(tv.toString());

        System.out.println("Для включения телевизора напишите 1");
        tv.pushPowerButton(scanner);
        scanner.nextLine();

        System.out.println("Выберите канал");
        tv.choiceNumberChanel(scanner);
        scanner.nextLine();
    }
}
