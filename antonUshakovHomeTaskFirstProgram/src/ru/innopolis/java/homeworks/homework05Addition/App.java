package ru.innopolis.java.homeworks.homework05Addition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        List<Television> televisions = new ArrayList<>();

        Television television1 = new Television("Samsung", 105);
        Television television2 = new Television("SONY", 115);
        Television television3 = new Television("Philips", 102);
        Television television4 = new Television("LG", 120);
        Television television5 = new Television("Xiaomi", 100);
        Television television6 = new Television("TOSHIBA", 115);
        Television television7 = new Television("Горизонт", 50);
        Television television8 = new Television("Hyundai", 55);
        Television television9 = new Television("Digma", 75);
        Television television10 = new Television("BBK", 150);

        functionTV(television1, televisions);
        functionTV(television2, televisions);
        functionTV(television3, televisions);
        functionTV(television4, televisions);
        functionTV(television5, televisions);
        functionTV(television6, televisions);
        functionTV(television7, televisions);
        functionTV(television8, televisions);
        functionTV(television9, televisions);
        functionTV(television10, televisions);

        List<Television> list2 = excludeTV(televisions);

        List<Television> list = list2
                .stream()
                .sorted(Comparator.comparing(Television::getNumberChanel))
                .toList();


        for (Television television : list) {
            System.out.println(television);
        }
    }

    //    вызов методов у объекта класса
    public static void functionTV(Television television, List<Television> televisions) {
        ternOn(television);
        ternOnChannel(television);
        settingVolume(television);
        televisions.add(television);
    }

    // Включение телевизора
    private static void ternOn(Television television) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Для включения телевизора напишите 1");
        television.pushPowerButton(scanner.nextInt());
    }

    //Выбор канала
    private static void ternOnChannel(Television television) {
        Scanner scanner = new Scanner(System.in);

        if (television.isTvIsOn()) {
            System.out.println("Выберите канал телепередачи:");
            while (true) {
                if (television.choiceNumberChanel(scanner.nextInt())) break;
            }
        } else {
            System.out.println("Компьютер выключен, выбор канала не возможен!");
        }
    }

    // Выбор громкости
    private static void settingVolume(Television television) {
        Scanner scanner = new Scanner(System.in);

        if (television.isTvIsOn()) {
            System.out.println("Выберите громкость телевизора:");
            while (true) {
                if (television.choiceVolume(scanner.nextInt())) break;
            }
        } else {
            System.out.println("Компьютер выключен, выбор громкости не возможен!");
        }
    }

    //    Исключаем телевизоры отключенные и у кого громкость не входит в диапазон от 50 до 70
    private static List<Television> excludeTV(List<Television> televisions) {
        List<Television> list2 = new ArrayList<>();
        for (Television television : televisions) {
            if (television.isTvIsOn() && television.getMaxVolume()
                    <= 70 && television.getMaxVolume() >= 50) {
                list2.add(television);
            }
        }
        return list2;
    }
}
