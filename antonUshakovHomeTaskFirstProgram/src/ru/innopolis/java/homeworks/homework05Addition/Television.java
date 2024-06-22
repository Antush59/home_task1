package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Objects;

public class Television {

    private String companyName;
    private boolean tvIsOn;
    private Integer diagonalSize;
    private Integer numberChanel;
    private Integer maxVolume;

    public Television(String companyName, int diagonalSize) {
        this.companyName = companyName;
        this.diagonalSize = diagonalSize;
    }

    //Включение телевизора.
    public void pushPowerButton(int pwButton) {
        if (pwButton == 1) {
            tvIsOn = true;
        } else {
            tvIsOn = false;
        }
    }

    //Выбор канала от 1 до 400. Исключен выбор канала, если телевизор выключен.
    //Исключен выбор канала больше 400  и ниже 0.
    public boolean choiceNumberChanel(Integer numberChanel) {
        this.numberChanel = numberChanel;
        if (numberChanel <= 400 && numberChanel > 0) {
            return true;
        } else {
            System.out.println("Выберите канал от 1 до 400");
            return false;
        }
    }

    //    Исключаем из выбора громкости уровень ниже 0 и выше 100
    public boolean choiceVolume(Integer volume) {
        this.maxVolume = volume;
        if (volume <= 100 && volume >= 0) {
            return true;
        } else {
            System.out.println("Выберите громкость в дапазоне от 0 до 100");
            return false;
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public boolean isTvIsOn() {
        return tvIsOn;
    }

    public Integer getDiagonalSize() {
        return diagonalSize;
    }

    public Integer getNumberChanel() {
        return numberChanel;
    }

    public Integer getMaxVolume() {
        return maxVolume;
    }

    public void setTvIsOn(boolean tvIsOn) {
        this.tvIsOn = tvIsOn;
    }

    @Override
    public String toString() {
        return "Television{" +
                "companyName='" + companyName + '\'' +
                ", diagonalSize=" + diagonalSize +
                ", tvIsOn=" + tvIsOn +
                ", numberChanel=" + numberChanel +
                ", maxVolume=" + maxVolume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Television that = (Television) o;
        return tvIsOn == that.tvIsOn && Objects.equals(companyName, that.companyName) && Objects.equals(diagonalSize, that.diagonalSize) && Objects.equals(numberChanel, that.numberChanel) && Objects.equals(maxVolume, that.maxVolume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, tvIsOn, diagonalSize, numberChanel, maxVolume);
    }
}
