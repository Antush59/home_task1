package ru.innopolis.java.homeworks.homework05Addition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        List<Television> televisions = new ArrayList<>();
        List<Channel> channelList = new ArrayList<>();

        channelList.add(new Channel("Первый канал", 1,
                List.of(new TelevisionProgram("Голос", 7.4, 123000))));
        channelList.add(new Channel("Россия", 2,
                List.of(new TelevisionProgram("Сто к одному", 5.3, 4500))));
        channelList.add(new Channel("НТВ", 3,
                List.of(new TelevisionProgram("Квартирный вопрос", 6.2, 71000))));
        channelList.add(new Channel("СТС", 4,
                List.of(new TelevisionProgram("Три Кота", 6.8, 121000))));
        channelList.add(new Channel("Пятница", 5,
                List.of(new TelevisionProgram("Беремена в 16", 9.8, 754320))));
        channelList.add(new Channel("ТНТ", 6,
                List.of(new TelevisionProgram("Импровизация", 7.7, 860000))));
        channelList.add(new Channel("Спас ТВ", 7,
                List.of(new TelevisionProgram("В поисках Бога", 6.4, 87000))));

        functionTV(new Television("Samsung", 105, channelList), televisions);
        functionTV(new Television("SONY", 115, channelList), televisions);
//        functionTV(new Television("Philips", 102, channelList), televisions);
//        functionTV(new Television("LG", 120, channelList), televisions);
//        functionTV(new Television("Xiaomi", 100, channelList), televisions);
//        functionTV(new Television("TOSHIBA", 115, channelList), televisions);
//        functionTV(new Television("Горизонт", 50, channelList), televisions);
//        functionTV(new Television("Hyundai", 55, channelList), televisions);
//        functionTV(new Television("Digma", 75, channelList), televisions);
//        functionTV(new Television("BBK", 150, channelList), televisions);

        for (Television television : televisions) {
            television.printTV(television);

        }
    }

    //    вызов методов у объекта класса
    public static void functionTV(Television television, List<Television> televisions) {
        turnOn(television);
        settingVolume(television);
        turnOnChannel(television);
        televisions.add(television);
    }

    // Включение телевизора
    private static void turnOn(Television television) {
        System.out.println("Для включения телевизора напишите: true");
        Scanner scanner = new Scanner(System.in);
        String resultTurnOn = scanner.nextLine();

        if (resultTurnOn.equals("true")) {
            television.pushPowerButton(true);
        } else {
            television.pushPowerButton(false);

        }
    }

    //Выбор канала и вывод данных канала в консоль
    private static void turnOnChannel(Television television) {
        Scanner scanner = new Scanner(System.in);
        if (television.isTvIsOn()) {
            System.out.println("Выберите канал телепередачи:");
            while (true) {
                if (television.setChannel(scanner.nextInt(), television.getChannelList().size())) {
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
                if (television.setVolume(scanner.nextInt())) break;
            }
        } else {
            System.out.println("Компьютер выключен, выбор громкости не возможен!");
        }
    }
}
