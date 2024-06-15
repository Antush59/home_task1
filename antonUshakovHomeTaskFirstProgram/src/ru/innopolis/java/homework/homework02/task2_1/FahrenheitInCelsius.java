package ru.innopolis.java.homework.homework02.task2_1;

import java.util.Scanner;

/**
 * Задача 1. Напишите Java-программу для преобразования температуры изФаренгейта в градусы Цельсия.
 */

public class FahrenheitInCelsius {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите степень в градусах Фарентгейта : ");
        double fahrenheit = scanner.nextDouble();
        double celsius = (fahrenheit - 32) / 1.8;
        System.out.println(fahrenheit + " градусов по Фарентгейту равна " + celsius + " по Цельсию");
    }
}
