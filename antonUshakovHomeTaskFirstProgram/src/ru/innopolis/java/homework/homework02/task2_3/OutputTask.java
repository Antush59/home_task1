package ru.innopolis.java.homework.homework02.task2_3;

import java.util.Scanner;

/**
 * Задача 3*. Напишите Java-программу для объединения данной строки ссамим собой заданное количество раз.
 */

public class OutputTask {
    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
        System.out.print("Введите целое число повторений: ");
        int count = sr.nextInt();
        sr.nextLine();
        System.out.print("Введите исходную строку: ");
        String word = sr.nextLine();
        String resultStrings = "";
        for (int i = count; i != 0; i--) {
            resultStrings += word;
        }
        System.out.println("Число повторения " + count + " раз: ");
        System.out.println(resultStrings);
    }
}
