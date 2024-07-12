package ru.innopolis.java.homeworks.homework04.task4_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrowsPattern {
    public static void main(String[] args) {
        String value = ">>-->-><<<><><>>--><--<<>>-->>><><--<<--<<>>>>----->>--><<---<<--<<<-<<>>-->>-->";
        String arrows = "(>>-->)|(<--<<)";
        Pattern pattern = Pattern.compile(arrows);
        Matcher matcher = pattern.matcher(value);
        int count = 0;

        while (matcher.find()) {
            count ++;
    }
        System.out.println("Количество повторений стрел: " + count);
    }
}
