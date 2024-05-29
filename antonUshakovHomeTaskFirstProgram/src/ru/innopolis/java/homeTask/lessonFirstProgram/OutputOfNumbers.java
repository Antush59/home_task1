package ru.innopolis.java.homeTask.lessonFirstProgram;


/**
 * Задача 1. Составить программу вывода на экран в одну строку четырех
 * любых чисел с тремя пробелами между ними.
 */

import java.util.Random;

public class OutputOfNumbers {
    public static void main(String[] args) {
        Random rd = new Random();
        System.out.println(rd.nextInt(100) + " " +
                rd.nextInt(100) + " " + rd.nextInt(100));
    }
}

