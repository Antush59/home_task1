package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Objects;

public class TelevisionProgram {

    private String programName;
    private Double rating;
    private Integer numberOfViewers;

    public TelevisionProgram(String programName, Double rating, Integer numberOfViewers) {
        this.programName = programName;
        this.rating = rating;
        this.numberOfViewers = numberOfViewers;
    }

    public String getProgramName() {
        return programName;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getNumberOfViewers() {
        return numberOfViewers;
    }

    @Override
    public String toString() {
        return "Программа: " + programName +
                ", рейтинг программы: " + rating +
                ", количество телезрителей: " + numberOfViewers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelevisionProgram that)) return false;
        return Objects.equals(getProgramName(), that.getProgramName()) && Objects.equals(getRating(), that.getRating()) && Objects.equals(getNumberOfViewers(), that.getNumberOfViewers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProgramName(), getRating(), getNumberOfViewers());
    }
}
