package ru.innopolis.java.homeTask.task4.task4_1;

import java.util.Scanner;

/**
 * Задача1.Для введенной с клавиатуры буквы английского алфавита нужно вывести слева стоящую букву на стандартной
 * клавиатуре. При этом клавиатура замкнута, т.е.справа от буквы «p» стоит буква «a», а слеваот "а" буква "р",
 * так же соседними считаются буквы «l» и буква «z» ,а буква «m» сбуквой «q».
 */

public class PreviousOnKeyboard {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строчную букву английского алфавита: ");
        String letterString = scanner.nextLine();

        char letter = letterString.charAt(0);

        charKeyboard(letter);
    }

    //Выдача значения предыдущей буквы с клавиатуры
    public static void charKeyboard(char letter) {
        String keyboardAlphabet = "qwertyuiopasdfghjklzxcvbnmq";
        char[] charArray = keyboardAlphabet.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (letter == charArray[i]) {
                System.out.println("Предыдущий символ с клавиатуры: " + charArray[i - 1]);
            }
        }
    }
}
