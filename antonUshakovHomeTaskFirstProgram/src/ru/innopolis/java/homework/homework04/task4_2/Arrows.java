package ru.innopolis.java.homework.homework04.task4_2;

/**
 * Задача2.Задана последовательность, состоящая только из символов ‘>’, ‘<’ и ‘-‘. Требуется найти количество
 * стрел, которые спрятаны в этой последовательности. Стрелы – это подстроки вида ‘>>-->’ и ‘<--<<’.
 */

public class Arrows {
    public static void main(String[] args) {

        String value = ">>-->-><<<><><>>--><--<<>>-->>><><--<<--<<>>>>----->>--><<---<<--<<<-<<>>-->>-->";
        String arrow1 = ">>-->";
        String arrow2 = "<--<<";
        int countArrow1 = countReplace(value, arrow1);
        int countArrow2 = countReplace(value, arrow2);
        int countSum = countArrow1 + countArrow2;

        System.out.println("Количество повторений стрел: " + countSum);

    }

    //    Метод, для подсчета встречающейся строки (arrow) в выражении (value)
    private static int countReplace(String value, String arrow) {

        int count = 0;
        int index = 0;

        while (index != -1) {
            index = value.indexOf(arrow);
            if (index == -1) {
                break;
            } else {
                value = value.substring(index + arrow.length());
                count++;
            }
        }
        return count;
    }
}
