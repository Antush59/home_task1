package ru.innopolis.java.additionalTask;

/**
 * 2. на вход подается строка, которая состоит из маленьких латинских букв.
 * Проверить, что в строке у нас встречаются все символы английского алфавита хотя бы 1 раз
 * asdsadqdwe -> false
 * qwertyuiopasdfghjklzxcvbnmljhjqenb
 */

public class EnglishAlphabet {
    public static void main(String[] args) {

        char[] arrayAlphabet = {'a', 'b', 'c','d', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        String example1 = "asdsadqdwe";
        String example2 = "qwertyuiopasdfghjklzxcvbnmljhjqenb";

        System.out.println(thereIsInString(arrayAlphabet, example1));
        System.out.println();
        System.out.println(thereIsInString(arrayAlphabet, example2));

    }

    private static boolean thereIsInString(char[] arrayAlphabet, String example) {
        int index = 0;
        example = example.toLowerCase();
        for (int i = 0; i < arrayAlphabet.length; i++) {
        index = example.indexOf(arrayAlphabet[i]);
            if (index == -1){
                System.out.println("В строке нет всех символов из английского алфавита");
                return false;
            }
        }
        System.out.println("В строке есть все символы из английского алфавита");
        return true;
    }
}
