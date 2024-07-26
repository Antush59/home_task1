package ru.innopolis.java.homeworks.homework11;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Car> carList = new ArrayList<>();
        String colorToFind;
        int mileageToFind;
        int n;
        int m;
        String modelToFind;
        String modelToFind2;
        Scanner scanner = new Scanner(System.in);

        addCar(carList);

        String[] value = scanner.nextLine().split(", ");
        colorToFind = value[0];
        mileageToFind = Integer.parseInt(value[1].replaceAll("[^0-9]", ""));

        String[] valueNM = scanner.nextLine().split(", ");
        n = Integer.parseInt(valueNM[0].replaceAll("[^0-9]", ""));
        m = Integer.parseInt(valueNM[1].replaceAll("[^0-9]", ""));

        modelToFind = addModelToFind();
        modelToFind2 = addModelToFind();

        printAll(carList);

        sortingByColorAndMileage(carList, colorToFind, mileageToFind);

        countUniqueCars(carList, n, m);

        colorOfCheapestCar(carList);

        averageCostOfCar(carList, modelToFind);

        averageCostOfCar(carList, modelToFind2);

    }

    //Средняя стоимость искомой модели
    private static void averageCostOfCar(List<Car> carList, String modelToFind) {
        IntSummaryStatistics intSummaryStatistics = carList.stream()
                .filter(o -> Objects.equals(o.getModelCar(), modelToFind))
                .mapToInt(Car::getCostCar)
                .summaryStatistics();
        System.out.print("Средняя стоимость модели " + modelToFind + ": ");
        System.out.println(intSummaryStatistics.getAverage());
    }

    //Вывод цвета автомобиля с минимальной стоимостью
    private static void colorOfCheapestCar(List<Car> carList) {
        System.out.print("Цвет автомобиля с минимальной стоимостью: ");
        carList.stream()
                .min(Comparator.comparing(Car::getCostCar))
                .stream()
                .map(Car::getColorCar)
                .forEach(System.out::println);
    }

    //Количество уникальных моделей в ценовом диапазоне от n до m тыс.
    private static void countUniqueCars(List<Car> carList, int n, int m) {
        System.out.print("Уникальные автомобили: ");
        long count = carList.stream()
                .filter(o -> o.getCostCar() >= n && o.getCostCar() <= m)
                .count();
        System.out.println(count + " шт.");
    }

    //Номера   всех   автомобилей,   имеющих   заданный   в   переменной   цвет colorToFind или нулевой пробег mileageToFind.
    private static void sortingByColorAndMileage(List<Car> carList, String colorToFind, int mileageToFind) {
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
    private static void printAll(List<Car> carList) {
        System.out.println("Автомобили в базе:\nNumber  Model  Color Mileage Cost");
        carList.forEach(System.out::println);
    }

    //Ввод автомобилей с консоли
    private static void addCar(List<Car> carList) {
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

    private static String addModelToFind() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
