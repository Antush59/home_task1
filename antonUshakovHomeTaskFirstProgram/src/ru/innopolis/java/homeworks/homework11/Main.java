package ru.innopolis.java.homeworks.homework11;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Car> carList = new ArrayList<>();

        addCar(carList);

        printAll(carList);

        sortingByColorAndMileage(carList);

        countUniqueCars(carList);

        colorOfCheapestCar(carList);

        averageCostOfCar(carList);

    }
//Средняя стоимость искомой модели
    private static void averageCostOfCar(List<Car> carList) {
        System.out.println("\nВведите модель автомобилей для рассчета средней стоимости в списке: ");
        Scanner scanner = new Scanner(System.in);
        String modelToFind = scanner.nextLine();
        IntSummaryStatistics intSummaryStatistics = carList.stream()
                .filter(o -> Objects.equals(o.getModelCar(), modelToFind))
                .mapToInt(Car::getCostCar)
                .summaryStatistics();
        System.out.print("\nСредняя стоимость модели " + modelToFind + ": ");
        System.out.println(intSummaryStatistics.getAverage());
    }

//Вывод цвета автомобиля с минимальной стоимостью
    private static void colorOfCheapestCar(List<Car> carList) {
        System.out.print("\nЦвет автомобиля с минимальной стоимостью: ");
        carList.stream()
                .min(Comparator.comparing(Car::getCostCar))
                .stream()
                .map(Car::getColorCar)
                .forEach(System.out::println);
    }

//Количество уникальных моделей в ценовом диапазоне от n до m тыс.
    private static void countUniqueCars(List<Car> carList) {
        System.out.println("\nВведите диапазон цен от и до через запятую через запятую для фильтрации списка:");
        Scanner scanner = new Scanner(System.in);
        String[] value = scanner.nextLine().split(", ");
        int n = Integer.parseInt(value[0].replaceAll("[^0-9]", ""));
        int m = Integer.parseInt(value[1].replaceAll("[^0-9]", ""));

        System.out.println("\nУникальные автомобили: ");
        long count = carList.stream()
                .filter(o -> o.getCostCar() >= n && o.getCostCar() <= m)
                .count();
        System.out.println(count + " шт.\n");
    }

//Номера   всех   автомобилей,   имеющих   заданный   в   переменной   цветcolorToFind или нулевой пробег mileageToFind.
    private static void sortingByColorAndMileage(List<Car> carList) {
        System.out.println("\n\nВведите цвет и пробег машины через запятую для фильтрации списка:");
        Scanner scanner = new Scanner(System.in);
        String[] value = scanner.nextLine().split(", ");
        String colorToFind = value[0];
        int mileageToFind = Integer.parseInt(value[1].replaceAll("[^0-9]", ""));
        System.out.print("\nНомера автомобилей по цвету или пробегу: ");
        carList.stream()
                .filter(o -> Objects.equals(o.getColorCar(), colorToFind) ||
                        Objects.equals(o.getMileageCar(), mileageToFind))
                .map(Car::getNumberCar)
                .map(o -> o + " ")
                .forEach(System.out::print);
    }

//Вывод всех автомобилей в базе
    private static void printAll(List<Car> carList) {
        System.out.println("\nАвтомобили в базе:\n[НОМЕР_АВТОМОБИЛЯ][МОДЕЛЬ][ЦВЕТ][ПРОБЕГ][ЦЕНА]");
        carList.forEach(System.out::println);
        System.out.println();
    }

//Ввод автомобилей с консоли
    private static void addCar(List<Car> carList) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String value = scanner.nextLine();
            if (Objects.equals(value, "END")) {
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
