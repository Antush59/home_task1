package ru.innopolis.java.homeworks.homework09.cars;

import java.util.Objects;

public class ShowCar extends Car {

    private int stars;

    public ShowCar() {
    }

    public ShowCar(String carBrand, String carModel, int yearOfRelease, int enginePower, int speedup,
                   int clearance, int serviceLife, int stars) {
        super(carBrand, carModel, yearOfRelease, enginePower, speedup, clearance, serviceLife);
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowCar showCar)) return false;
        if (!super.equals(o)) return false;
        return stars == showCar.stars;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), stars);
    }

    @Override
    public String toString() {
        return "ShowCar{" +
                "stars=" + stars +
                "} " + super.toString();
    }
}
