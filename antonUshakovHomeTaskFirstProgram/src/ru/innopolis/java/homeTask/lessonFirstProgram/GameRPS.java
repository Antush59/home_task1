package ru.innopolis.java.homeTask.lessonFirstProgram;

import java.util.Random;

/**
 * Задача 2*. Вася и Петя играют в игру “Камень, ножницы, бумага”.
 * Каждый из них показывает свою фигуру камень-0, ножницы-1, бумага-2.
 * Программа определяет, кто из них выиграл.
 * Выбор каждого участника формируется случайным образом.
 */

public class GameRPS {
    public static void main(String[] args) {
        Random rd = new Random();
        String firstPlayer = "Вася";
        String secondPlayer = "Петя";

//      Объявил переменные для рандомного выбора.
        int player1 = rd.nextInt(3);
        int player2 = rd.nextInt(3);

//      Решил вывести проверку выполнения программы в виде визуализации в командной строке
//      результата выпавших элементов у игроков
        String [] RPS = {"Камень", "Ножницы","Бумага"};

        if (player1 == player2) {
            System.out.println("Ничья");
        } else if (player1 == 0) {
            if (player2 == 1) {
                System.out.println("Выиграл игрок " + firstPlayer);
            } else if (player2 == 2) {
                System.out.println("Выиграл игрок " + secondPlayer);
            }
        } else if (player1 == 1) {
            if (player2 == 0) {
                System.out.println("Выиграл игрок " + secondPlayer);
            } else if (player2 == 2) {
                System.out.println("Выиграл игрок " + firstPlayer);
            }
        } else if (player1 == 2) {
            if (player2 == 0) {
                System.out.println("Выиграл игрок " + firstPlayer);
            } else if (player2 == 1) {
                System.out.println("Выиграл игрок " + secondPlayer);
            }
        }

        System.out.println("Игрок " + firstPlayer + " выкинул: " + RPS[player1]);
        System.out.println("Игрок " + secondPlayer + " выкинул: " + RPS[player2]);
    }
}
