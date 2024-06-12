package ru.innopolis.java.homeTask.task3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Television television1 = new Television("SAMSUNG", 105);
        Television television2 = new Television("SONY", 115);

        usingTelevision(television1);

        System.out.println();

        usingTelevision(television2);
    }

//Создали метод для реализации работы с телевизором
    private static void usingTelevision(Television television) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(television);

        System.out.println("Для включения телевизора напишите 1");
        television.pushPowerButton(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Выбор канала телепередачи");
        if (television.isPowerButton()){
            System.out.println("Выберите канал телепередачи:");
            while (true) {
                if (television.choiceNumberChanel(scanner.nextInt())) break;
            }
        } else {
            System.out.println("Компьютер выключен, выбор канала не возможен!");
        }
    }
}
