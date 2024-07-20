package ru.innopolis.java.homeworks.homework09.races;

import ru.innopolis.java.homeworks.homework09.cars.Car;
import ru.innopolis.java.homeworks.homework09.cars.ShowCar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DriftRace extends Race{

    public DriftRace(int raceLength, String raceRoute, int prizeMoney) {
        super(raceLength, raceRoute, prizeMoney);
    }

    @Override
    public void choosingWinner(List<Car> listCars, Path pathOut) throws IOException {
        Car carWinner = new Car();
        for (int i = 0; i < listCars.size() - 1; i++) {

            if (listCars.get(i).getEnginePower() + listCars.get(i).getClearance() >=
                    listCars.get(i + 1).getEnginePower() + listCars.get(i + 1).getClearance()) {
                carWinner = listCars.get(i);
            } else {
                carWinner = listCars.get(i + 1);
            }
        }

        if (carWinner instanceof ShowCar) {
            ((ShowCar) carWinner).setStars(((ShowCar) carWinner).getStars());
        }

        String raceFinal = "На гонке: " + this + "\n" + "Победу одержала машина: " + carWinner + "\n";
        Files.writeString(pathOut, raceFinal, StandardOpenOption.APPEND);
    }

    @Override
    public String toString() {
        return "DriftRace{" +
                "raceLength=" + raceLength +
                ", raceRoute='" + raceRoute + '\'' +
                ", prizeMoney=" + prizeMoney +
                "} ";
    }
}
