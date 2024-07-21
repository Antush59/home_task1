package ru.innopolis.java.homeworks.homework09.races;

import ru.innopolis.java.homeworks.homework09.cars.Car;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Race {

    protected int raceLength;
    protected String raceRoute;
    protected int prizeMoney;
    protected List<Car> listCars;

    public Race() {
    }

    public Race(int raceLength, String raceRoute, int prizeMoney) {
        this.raceLength = raceLength;
        this.raceRoute = raceRoute;
        this.prizeMoney = prizeMoney;

    }

    public void choosingWinner(List<Car> listCars, Path pathOut) throws IOException {
    }

    public int getRaceLength() {
        return raceLength;
    }

    public String getRaceRoute() {
        return raceRoute;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public List<Car> getListCars() {
        return listCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Race race)) return false;
        return raceLength == race.raceLength && prizeMoney == race.prizeMoney && Objects.equals(raceRoute, race.raceRoute) && Objects.equals(listCars, race.listCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceLength, raceRoute, prizeMoney, listCars);
    }

}