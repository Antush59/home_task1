package ru.innopolis.java.homeworks.homework10;

import java.util.stream.IntStream;

public class Sequence {

    public static int[] filter(int[] array, ByCondition condition) {

        return IntStream.of(array)
                .filter(condition::isOk)
                .toArray();
    }
}
