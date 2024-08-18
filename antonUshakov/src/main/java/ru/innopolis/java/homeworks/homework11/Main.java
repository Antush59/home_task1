package ru.innopolis.java.homeworks.homework11;

import java.util.*;

public class Main {

    private static final List<Car> carList = new ArrayList<>();

    public static void main(String[] args) {

        String colorToFind;
        int mileageToFind;
        int minCost;
        int maxCost;
        String modelToFind;
        String modelToFind2;
        Scanner scanner = new Scanner(System.in);

        setCar();

        System.out.println("Введите цвет и кол-во милей через запятую для фильтрации");
        String[] value = scanner.nextLine().split(", ");
        colorToFind = value[0];
        mileageToFind = Integer.parseInt(value[1].replaceAll("[^0-9]", ""));

        System.out.println("Введите минимальную и максимальную цену авто через запятую для фильтрации");
        String[] valueNM = scanner.nextLine().split(", ");
        minCost = Integer.parseInt(valueNM[0].replaceAll("[^0-9]", ""));
        maxCost = Integer.parseInt(valueNM[1].replaceAll("[^0-9]", ""));

        modelToFind = scanner.nextLine();
        modelToFind2 = scanner.nextLine();

        printAll();

        sortingByColorAndMileage(colorToFind, mileageToFind);

        countUniqueCars(minCost, maxCost);

        colorOfCheapestCar();

        averageCostOfCar(modelToFind);

        averageCostOfCar(modelToFind2);

    }

    //Средняя стоимость искомой модели
    private static void averageCostOfCar(String modelToFind) {
        IntSummaryStatistics intSummaryStatistics = carList.stream()
                .filter(o -> Objects.equals(o.getModelCar(), modelToFind))
                .mapToInt(Car::getCostCar)
                .summaryStatistics();
        System.out.print("Средняя стоимость модели " + modelToFind + ": ");
        System.out.println(intSummaryStatistics.getAverage());
    }

    //Вывод цвета автомобиля с минимальной стоимостью
    private static void colorOfCheapestCar() {
        System.out.print("Цвет автомобиля с минимальной стоимостью: ");
        carList.stream()
                .min(Comparator.comparing(Car::getCostCar))
                .ifPresent(car -> System.out.println(car.getColorCar()));
    }

    //Количество уникальных моделей в ценовом диапазоне от minCost до maxCost тыс.
    private static void countUniqueCars(int minCost, int maxCost) {
        System.out.print("Уникальные автомобили: ");
        long count = carList.stream()
                .filter(o -> o.getCostCar() >= minCost && o.getCostCar() <= maxCost)
                .count();
        System.out.println(count + " шт.");
    }

    //Номера   всех   автомобилей,   имеющих   заданный   в   переменной
    // цвет colorToFind или нулевой пробег mileageToFind.
    private static void sortingByColorAndMileage(String colorToFind, int mileageToFind) {
        System.out.print("\nНомера автомобилей по цвету или пробегу: ");
        carList.stream()
                .filter(o -> Objects.equals(o.getColorCar(), colorToFind) ||
                        Objects.equals(o.getMileageCar(), mileageToFind))
                .map(Car::getNumberCar)
                .map(o -> o + " ")
                .forEach(System.out::print);
        System.out.println();
    }

    //Вывод всех автомобилей в базе
    private static void printAll() {
        System.out.println("Автомобили в базе:\nNumber  Model  Color Mileage Cost");
        carList.forEach(System.out::println);
    }

    //Ввод автомобилей с консоли
    private static void setCar() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String value = scanner.nextLine();
            if (Objects.equals(value, "")) {
                break;
            } else {
                String[] arraysCar = value.split("\\|");
                Car car = new Car(arraysCar[0], arraysCar[1], arraysCar[2], Integer.parseInt(arraysCar[3]),
                        Integer.parseInt(arraysCar[4]));
                carList.add(car);
            }
        }
    }
}
