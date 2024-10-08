package ru.innopolis.java.homeworks.homework09.cars;

import java.util.Arrays;
import java.util.Objects;

public class PerformanceCar extends Car {

    private String[] addOns;

    public PerformanceCar() {
    }

    public PerformanceCar(String carBrand, String carModel, int yearOfRelease,
                          long enginePower, int speedup, int clearance, int serviceLife) {
        super(carBrand, carModel, yearOfRelease, (int) (enginePower * 1.5), speedup, (int) (clearance * 0.75), serviceLife);    }

    public void setAddOns(String[] addOns) {
        this.addOns = addOns;
    }

    @Override
    public int getEnginePower() {
        return enginePower;
    }

    @Override
    public int getClearance() {
        return clearance;
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
