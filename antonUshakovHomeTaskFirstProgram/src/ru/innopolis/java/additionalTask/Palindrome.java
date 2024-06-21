package ru.innopolis.java.additionalTask;

import java.util.Scanner;

/**
 * напишите метод проверки, я вляется ли строка палиндромом
 */

public class Palindrome {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите строку");
        String firstString = sc.nextLine().toLowerCase();
        String onlyLetters = onlyLetters(firstString).intern();

        String reverse = reverseString(onlyLetters).intern();

        comparison(onlyLetters, reverse);
    }

    public static String onlyLetters (String str) {
        return str.replaceAll("[^a-zа-я]", "");
    }

    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void comparison (String firstString, String reversString) {
        if (firstString == reversString) {
            System.out.println("Строка полиндром");
        } else {
            System.out.println("Строка не полиндром");
        }
    }
}
