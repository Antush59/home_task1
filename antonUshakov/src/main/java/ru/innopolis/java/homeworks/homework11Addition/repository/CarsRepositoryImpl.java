package ru.innopolis.java.homeworks.homework11Addition.repository;

import ru.innopolis.java.homeworks.homework11Addition.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class CarsRepositoryImpl implements CarsRepository {

    List<Car> carList = new ArrayList<>();

    @Override
    public void setCars(Path pathInput, Path pathOutput) {
        List<String> strings;
        try {
            strings = Files.readAllLines(pathInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String string : strings) {
            if (string.contains("|")) {
                String[] arraysCar = string.split("\\|");
                Car car = new Car(arraysCar[0], arraysCar[1], arraysCar[2], Integer.parseInt(arraysCar[3]),
                        Integer.parseInt(arraysCar[4]));
                try {
                    Files.writeString(pathOutput, car + "\n", StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                carList.add(car);
            }
        }
    }

    @Override
    public void sortingByColorAndMileage(String colorToFind, int mileageToFind,
                                         Path pathOutput) {
        String value = "\nНомера автомобилей по цвету или пробегу: ";
        try {
            Files.writeString(pathOutput, value, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        carList.stream()
                .filter(o -> Objects.equals(o.getColorCar(), colorToFind) ||
                        Objects.equals(o.getMileageCar(), mileageToFind))
                .map(Car::getNumberCar)
                .map(o -> o + " ")
                .forEach(o -> {
                    try {
                        Files.writeString(pathOutput, o, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void countUniqueCars(int minCost, int maxCost, Path pathOutput) {
        long count = carList.stream()
                .filter(o -> o.getCostCar() >= minCost && o.getCostCar() <= maxCost)
                .count();
        String value = "\nУникальные автомобили: " + count + " шт.";
        try {
            Files.writeString(pathOutput, value, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void colorOfCheapestCar(Path pathOutput) {
        String value = "\nЦвет автомобиля с минимальной стоимостью: ";
        try {
            Files.writeString(pathOutput, value, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        carList.stream()
                .min(Comparator.comparing(Car::getCostCar))
                .ifPresent(o -> {
                    try {
                        Files.writeString(pathOutput, o.getColorCar(), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void averageCostOfCar(String modelToFind, Path pathOutput) {
        IntSummaryStatistics intSummaryStatistics = carList.stream()
                .filter(o -> Objects.equals(o.getModelCar(), modelToFind))
                .mapToInt(Car::getCostCar)
                .summaryStatistics();
        String value = "\nСредняя стоимость модели " + modelToFind + ": " + intSummaryStatistics.getAverage();
        try {
            Files.writeString(pathOutput, value, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
