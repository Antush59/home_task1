package ru.innopolis.java.homeTask.task4.tsk4_3;

import java.util.Arrays;

/**
 * Задача3*. Задана строка, состоящая из букв английского алфавита, разделенных одним пробелом.
 * Необходимо каждую последовательность символов упорядочить по возрастанию и вывести слова в нижнем регистре.
 */

public class ArrangeString {
    public static void main(String[] args) {

        String string = "bcatsAAbbEEE CCCkkkMMMUUUUbbb";

        String newString = string.toLowerCase();

        String[] arrayStrings = newString.split(" ");

        char[] charArray1 = arrayStrings[0].toCharArray();
        char[] charArray2 = arrayStrings[1].toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        System.out.println(charArray1);
        System.out.println(charArray2);
    }
}
