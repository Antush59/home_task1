package ru.innopolis.java.homeworks.homework05Addition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        List<Television> televisions = new ArrayList<>();
        List<Chanel> chanelList = new ArrayList<>();

        chanelList.add(new Chanel("Первый канал", 1, new TelevisionProgram("Голос", 7.4, 123000)));
        chanelList.add(new Chanel("Россия", 2, new TelevisionProgram("Сто к одному", 5.3, 4500)));
        chanelList.add(new Chanel("НТВ", 3, new TelevisionProgram("Квартирный вопрос", 6.2, 71000)));
        chanelList.add(new Chanel("СТС", 4, new TelevisionProgram("Три Кота", 6.8, 121000)));
        chanelList.add(new Chanel("Пятница", 5, new TelevisionProgram("Беремена в 16", 9.8, 754320)));
        chanelList.add(new Chanel("ТНТ", 6, new TelevisionProgram("Импровизация", 7.7, 860000)));
        chanelList.add(new Chanel("Спас ТВ", 7, new TelevisionProgram("В поисках Бога", 6.4, 87000)));

        functionTV(new Television("Samsung", 105, chanelList), televisions);
        functionTV(new Television("SONY", 115, chanelList), televisions);
        functionTV(new Television("Philips", 102, chanelList), televisions);
//        functionTV(new Television("LG", 120, chanelList), televisions);
//        functionTV(new Television("Xiaomi", 100, chanelList), televisions);
//        functionTV(new Television("TOSHIBA", 115, chanelList), televisions);
//        functionTV(new Television("Горизонт", 50, chanelList), televisions);
//        functionTV(new Television("Hyundai", 55, chanelList), televisions);
//        functionTV(new Television("Digma", 75, chanelList), televisions);
//        functionTV(new Television("BBK", 150, chanelList), televisions);

        for (Television television : televisions) {
            television.printTV(television);

        }
    }

    //    вызов методов у объекта класса
    public static void functionTV(Television television, List<Television> televisions) {
        ternOn(television);
        settingVolume(television);
        ternOnChannel(television);
        televisions.add(television);
    }

    // Включение телевизора
    private static void ternOn(Television television) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Для включения телевизора напишите 1");
        television.pushPowerButton(scanner.nextInt());
    }

    //Выбор канала и вывод данных канала в консоль
    private static void ternOnChannel(Television television) {
        Scanner scanner = new Scanner(System.in);
        if (television.isTvIsOn()) {
            System.out.println("Выберите канал телепередачи:");
            while (true) {
                if (television.choiceNumberChanel(scanner.nextInt(), television.getChanelList().size())) {
                    break;
                }
            }
        } else {
            System.out.println("Компьютер выключен, выбор канала не возможен!");
        }
    }

    // Выбор громкости
    private static void settingVolume(Television television) {
        Scanner scanner = new Scanner(System.in);
        if (television.isTvIsOn()) {
            while (true) {
                System.out.println("Выберите громкость телевизора:");
                if (television.choiceVolume(scanner.nextInt())) break;
            }
        } else {
            System.out.println("Компьютер выключен, выбор громкости не возможен!");
        }
    }
}
