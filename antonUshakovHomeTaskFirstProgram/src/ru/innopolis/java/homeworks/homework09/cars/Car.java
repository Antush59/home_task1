package ru.innopolis.java.homeworks.homework09.cars;

import java.util.Objects;

public class Car {

    protected String carBrand;
    protected String carModel;
    protected int yearOfRelease;
    protected int enginePower;
    protected int speedup;
    protected int clearance;
    protected int serviceLife;

    public Car() {}

    public Car(String carBrand, String carModel, int yearOfRelease, int enginePower,
               int speedup, int clearance, int serviceLife) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.yearOfRelease = yearOfRelease;
        this.enginePower = enginePower;
        this.speedup = speedup;
        this.clearance = clearance;
        this.serviceLife = serviceLife;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public int getSpeedup() {
        return speedup;
    }

    public int getClearance() {
        return clearance;
    }

    public int getServiceLife() {
        return serviceLife;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public void setSpeedup(int speedup) {
        this.speedup = speedup;
    }

    public void setClearance(int clearance) {
        this.clearance = clearance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", enginePower=" + getEnginePower() +
                ", speedup=" + speedup +
                ", clearance=" + getClearance() +
                ", serviceLife=" + serviceLife +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return yearOfRelease == car.yearOfRelease && enginePower == car.enginePower && speedup == car.speedup
                && clearance == car.clearance && serviceLife == car.serviceLife
                && Objects.equals(carBrand, car.carBrand) && Objects.equals(carModel, car.carModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carBrand, carModel, yearOfRelease, enginePower, speedup, clearance, serviceLife);
    }
}
