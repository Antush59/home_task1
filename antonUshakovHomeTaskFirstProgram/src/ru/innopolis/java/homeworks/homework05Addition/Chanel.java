package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Objects;

public class Chanel {

    private String nameChanel;
    private Integer number;
    private TelevisionProgram listPrograms;

    public Chanel(String nameChanel, Integer number, TelevisionProgram listPrograms) {
        this.nameChanel = nameChanel;
        this.number = number;
        this.listPrograms = listPrograms;
    }

    public String getNameChanel() {
        return nameChanel;
    }

    public Integer getNumber() {
        return number;
    }

    public TelevisionProgram getPrograms() {
        return listPrograms;
    }

    @Override
    public String toString() {
        return "Номер канала: " + number + ", Канал: " + nameChanel +
                ", телепередача: " + listPrograms.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chanel chanel)) return false;
        return Objects.equals(getNameChanel(), chanel.getNameChanel()) && Objects.equals(getNumber(), chanel.getNumber()) && Objects.equals(getPrograms(), chanel.getPrograms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameChanel(), getNumber(), getPrograms());
    }
}
