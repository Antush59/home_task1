package ru.innopolis.java.homeTask.tasks2.task2_2;

import java.util.Scanner;

/**
 * Задача 2.  Напишите программу на Java, которая принимает два целыхчисла от пользователя,
 * а затем печатает сумму, разницу, произведение, среднеезначение, расстояние (разница между целыми числами),
 * максимум (большее издвух целых чисел), минимум (меньшее из двух целых чисел).
 */

public class Arithmetic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите первое целое число: ");
        int firstNumber = sc.nextInt();
        System.out.print("Введите второе целое число: ");
        int secondNumber = sc.nextInt();

        System.out.println("Сумма двух целых чисел: " + (firstNumber + secondNumber));
        System.out.println("Разность двух целых чисел: " + (firstNumber - secondNumber));
        System.out.println("Произведение из двух целых чисел: " + (firstNumber * secondNumber));
        System.out.println("Среднее значение из двух целых чисел: " + (double)((firstNumber + secondNumber)/2));
        System.out.println("Расстояние двух целых чисел: " + ("Расстояние двух целых чисел: " +
                Math.abs(firstNumber - secondNumber)));
        System.out.println("Максимальное целое число: " + Math.max(firstNumber, secondNumber));
        System.out.println("Максимальное целое число: " + Math.min(firstNumber, secondNumber));
    }
}
