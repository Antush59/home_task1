package ru.innopolis.java.homeworks.homework13.utils;

public class DataHandling {

    private int parseCount(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Невалидное значение");
        }
    }

    public Integer validateCount(String value) {
        try {
            return parseCount(value);
        } catch (NumberFormatException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    private Double parseNumber(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Невалидное значение");
        }
    }

    public Double validateNumber(String value) {
        try {
            return parseNumber(value);
        } catch (NumberFormatException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
