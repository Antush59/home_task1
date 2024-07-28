package ru.innopolis.java.homeworks.homework11Addition.test;

import ru.innopolis.java.homeworks.homework11Addition.repository.CarsRepository;
import ru.innopolis.java.homeworks.homework11Addition.repository.CarsRepositoryImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        CarsRepository carsRepository = new CarsRepositoryImpl();

        Path pathInput = Path.of("antonUshakovHomeTaskFirstProgram/src/ru/" +
                "innopolis/java/homeworks/homework11Addition/data/cars.txt");
        Path pathOutput = Path.of("antonUshakovHomeTaskFirstProgram/src/ru/" +
                "innopolis/java/homeworks/homework11Addition/data/resultCars.txt");

        String colorToFind;
        int mileageToFind;
        int minCost;
        int maxCost;
        String modelToFind;
        String modelToFind2;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите цвет и кол-во милей через запятую для фильтрации:");
        String[] value = scanner.nextLine().split(", ");
        colorToFind = value[0];
        mileageToFind = Integer.parseInt(value[1].replaceAll("[^0-9]", ""));

        System.out.println("Введите минимальную и максимальную цену авто через запятую для фильтрации:");
        String[] valueNM = scanner.nextLine().split(", ");
        minCost = Integer.parseInt(valueNM[0].replaceAll("[^0-9]", ""));
        maxCost = Integer.parseInt(valueNM[1].replaceAll("[^0-9]", ""));

        System.out.println("Введите модель авто для средней стоимости его в списке:");
        modelToFind = scanner.nextLine();
        modelToFind2 = scanner.nextLine();

        String heading = "\nАвтомобили в базе:\nNumber  Model  Color Mileage Cost\n";
        Files.writeString(pathOutput, heading, StandardOpenOption.APPEND);

        //Ввод автомобилей с файла и запись в файл сиска машин
        carsRepository.setCars(pathInput, pathOutput);

        //Вывод номеров всех автомобилей в файл, имеющих заданный в переменной
        //цвет colorToFind или нулевой пробег mileageToFind.
        carsRepository.sortingByColorAndMileage(colorToFind, mileageToFind, pathOutput);

        //Вывод в файл количество уникальных моделей в ценовом диапазоне от minCost до maxCost тыс.
        carsRepository.countUniqueCars(minCost, maxCost, pathOutput);

        //Вывод цвета автомобиля с минимальной стоимостью
        carsRepository.colorOfCheapestCar(pathOutput);

        //Вывод в файл средней стоимости искомой модели
        carsRepository.averageCostOfCar(modelToFind, pathOutput);
        carsRepository.averageCostOfCar(modelToFind2, pathOutput);
    }

}
