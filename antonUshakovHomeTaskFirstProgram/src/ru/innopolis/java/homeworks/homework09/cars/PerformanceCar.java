package ru.innopolis.java.homeworks.homework09.cars;

import java.util.Arrays;
import java.util.Objects;

public class PerformanceCar extends Car{

    private String[] addOns;

    public PerformanceCar() {}

    public PerformanceCar(String carBrand, String carModel, int yearOfRelease,
                          int enginePower, int speedup, int clearance, int serviceLife) {
        super(carBrand, carModel, yearOfRelease, enginePower, speedup, clearance, serviceLife);
    }

    public PerformanceCar(String[] stringCar) {
        carBrand = stringCar[0];
        carModel = stringCar[1];

    }

    public void setAddOns(String[] addOns) {
        this.addOns = addOns;
    }

    @Override
    public int getEnginePower() {
        return (int) (enginePower *1.5);
    }

    @Override
    public int getClearance() {
        return (int) (clearance * 0.75);
    }

    public String[] getAddOns() {
        return addOns;
    }

    @Override
    public String toString() {
        return "PerformanceCar{" +
                "addOns=" + Arrays.toString(addOns) +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerformanceCar that)) return false;
        if (!super.equals(o)) return false;
        return Objects.deepEquals(addOns, that.addOns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(addOns));
    }
}
