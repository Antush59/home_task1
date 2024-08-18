package ru.innopolis.java.homeworks.homework10;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] integers = {1, 2, 3, 4, 8, 10, 11, 15, 18, 21, 30, 121, 200, 211, 212};

        ByCondition byCondition1 = (i) -> i % 2 == 0;
        ByCondition byCondition2 = (i) -> {
                int a,b;
                a = i % 10;
                b = (i /10) % 10;
                return (a + b) % 2 == 0;
        };

        int[] result = Sequence.filter(integers, byCondition1);
        int[] result2 = Sequence.filter(integers, byCondition2);

        System.out.println(Arrays.toString(integers));
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(Sequence.filter(result, byCondition2)));

        System.out.println();
        System.out.println(Arrays.toString(integers));
        System.out.println(Arrays.toString(result2));
    }
}
