package ru.innopolis.java.homeworks.homework05Addition;

import java.util.List;
import java.util.Objects;

public class Channel {

    private final String nameChannel;
    private final Integer number;
    private List<TelevisionProgram> listPrograms;

    public Channel(String nameChannel, Integer number, List<TelevisionProgram> listPrograms) {
        this.nameChannel = nameChannel;
        this.number = number;
        this.listPrograms = listPrograms;
    }

    public String getNameChannel() {
        return nameChannel;
    }

    public Integer getNumber() {
        return number;
    }

    public List<TelevisionProgram> getPrograms() {
        return listPrograms;
    }

    @Override
    public String toString() {
        return "Номер канала: " + number + ", Канал: " + nameChannel +
                ", телепередача: " + listPrograms.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel chanel)) return false;
        return Objects.equals(getNameChannel(), chanel.getNameChannel()) && Objects.equals(getNumber(), chanel.getNumber()) && Objects.equals(getPrograms(), chanel.getPrograms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameChannel(), getNumber(), getPrograms());
    }
}
