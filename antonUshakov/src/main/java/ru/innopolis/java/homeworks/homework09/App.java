package ru.innopolis.java.homeworks.homework09;

import ru.innopolis.java.homeworks.homework09.cars.Car;
import ru.innopolis.java.homeworks.homework09.cars.PerformanceCar;
import ru.innopolis.java.homeworks.homework09.cars.ShowCar;
import ru.innopolis.java.homeworks.homework09.races.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        Path pathInput = Path.of("home_task1/antonUshakov/src/main/java/ru/innopolis/java/homeworks/homework09/inputData.txt");
        Path pathWinner = Path.of("home_task1/antonUshakov/src/main/java/ru/innopolis/java/homeworks/homework09/Winner");
        List<String> strings = Files.readAllLines(pathInput, StandardCharsets.UTF_8);
        List<Car> cars = new ArrayList<>();
        DragRace dragRace = new DragRace(402, "Стадион", 1500000);
        CasualRace casualRace = new CasualRace(15000, "Городской", 3000000);
        DriftRace driftRace = new DriftRace(1500, "Стадион", 750000);
        TimeLimitRace timeLimitRace = new TimeLimitRace(2500, "Городской", 1000000, 55);
        CircuitRace circuitRace = new CircuitRace(1700, "Стадион", 1200000, 3);
        List<Race> races = List.of(dragRace, casualRace, driftRace, timeLimitRace, circuitRace);

        addingCars(strings, cars);
        randomRaceAndCars(cars, races, pathWinner);

    }

    public static void addingCars(List<String> strings, List<Car> cars) {
        for (String string : strings) {
            String[] stringCar = string.split(", ");
            if (stringCar.length == 7) {
                PerformanceCar performanceCar = new PerformanceCar(stringCar[0], stringCar[1],
                        Integer.parseInt(stringCar[2]), Integer.parseInt(stringCar[3]), Integer.parseInt(stringCar[4]),
                        Integer.parseInt(stringCar[5]), Integer.parseInt(stringCar[6]));
                cars.add(performanceCar);
            } else {
                ShowCar showCar = new ShowCar(stringCar[0], stringCar[1], Integer.parseInt(stringCar[2]),
                        Integer.parseInt(stringCar[3]), Integer.parseInt(stringCar[4]), Integer.parseInt(stringCar[5]),
                        Integer.parseInt(stringCar[6]), Integer.parseInt(stringCar[7]));
                cars.add(showCar);
            }
        }
    }

    public static void randomRaceAndCars(List<Car> cars, List<Race> races, Path pathWinner) throws IOException {
        int counter = 0;
        int randomRace = (int) (Math.random() * races.size());

        List<Car> participateInRace = new ArrayList<>();
        while (counter <= 4) {
            int choice = (int) (Math.random() * cars.size());
            if (cars.get(choice) != null) {
                participateInRace.add(cars.get(choice));
                cars.remove(choice);
                counter++;
            } else continue;
        }

        races.get(randomRace).choosingWinner(participateInRace, pathWinner);
        Garage garage = new Garage(cars);
        garage.upgradeCar();
    }
}
