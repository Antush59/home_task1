package ru.innopolis.java.homeworks.homework11;

public class Car {
    private final String numberCar;
    private final String modelCar;
    private String colorCar;
    private Integer mileageCar;
    private Integer costCar;

    public Car(String numberCar, String modelCar, String colorCar, Integer mileageCar, Integer costCar) {
        this.numberCar = numberCar;
        this.modelCar = modelCar;
        this.colorCar = colorCar;
        this.mileageCar = mileageCar;
        this.costCar = costCar;

    }

    public String getNumberCar() {
        return numberCar;

    }

    public String getModelCar() {
        return modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public Integer getMileageCar() {
        return mileageCar;
    }

    public void setMileageCar(Integer mileageCar) {
        this.mileageCar = mileageCar;
    }

    public Integer getCostCar() {
        return costCar;
    }

    public void setCostCar(Integer costCar) {
        this.costCar = costCar;
    }

    @Override
    public String toString() {
        return numberCar + " " + modelCar + " " + colorCar + " " + mileageCar + " " + costCar;
    }
}
