package ru.innopolis.java.homeworks.homework11Addition.repository;

import java.nio.file.Path;

public interface CarsRepository {

    void setCars(Path pathInput, Path pathOutput);

    void sortingByColorAndMileage(String colorToFind, int mileageToFind, Path pathOutput);

    void countUniqueCars(int minCost, int maxCost, Path pathOutput);

    void colorOfCheapestCar(Path pathOutput);

    void averageCostOfCar(String modelToFind, Path pathOutput);

}
