package ru.innopolis.java.homeTask.tasks2.task2_4;

import java.util.Scanner;

/**
 * Задача 4*. Напишите программу на Java для печати сетки из заданныхэлементов.
 */

public class Grid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число строк и столбцов: ");
        int countColumns;
        int countStrings = scanner.nextInt();
        countColumns = countStrings;
        scanner.nextLine();
        System.out.print("Введите повторяемый элемент сетки: ");
        String element = scanner.nextLine();
        System.out.println();
        for (int i = 0; i < countColumns; i++) {
            for (int j = 0; j < countStrings ; j++) {
                System.out.print(element);
            }
            System.out.println();
        }
    }
}
