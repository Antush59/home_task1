package ru.innopolis.java.homeworks.homework05Addition;

import java.util.List;
import java.util.Objects;

public class Television {

    private String companyName;
    private boolean tvIsOn;
    private Integer diagonalSize;
    private Integer numberChanel;
    private Integer maxVolume;
    private List<Chanel> chanelList;

    public Television(String companyName, int diagonalSize, List<Chanel> chanelList) {
        this.companyName = companyName;
        this.diagonalSize = diagonalSize;
        this.chanelList = chanelList;
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
    public boolean choiceNumberChanel(Integer numberChanel, int countChanel) {
        this.numberChanel = numberChanel;
        if (numberChanel <= countChanel && numberChanel > 0) {
            return true;
        } else {
            System.out.println("Выберите канал от 1 до " + countChanel);
            return false;
        }
    }

    //    Исключаем из выбора громкости уровень ниже 0 и выше 100
    public boolean choiceVolume(Integer volume) {
        this.maxVolume = volume;
        if (volume <= 80 && volume >= 20) {
            return true;
        } else {
            System.out.println("Выберите громкость в дапазоне от 20 до 80");
            return false;
        }
    }

    public List<Chanel> getChanelList() {
        return chanelList;
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

    public void printTV(Television television) {
        if (television.isTvIsOn()) {
            System.out.println("\n" + "Информация о телевизоре:");
            System.out.println("Телевизор компании " + television.getCompanyName() + " с диагональю " + television.getDiagonalSize()
                    + " включен, " + " выставлена громкость " + television.getMaxVolume() + "%, " + "включен " + television.getNumberChanel() + "канал");
            for (Chanel chanel : television.getChanelList()) {
                if (Objects.equals(numberChanel, chanel.getNumber())) {
                    System.out.println("О канале:");
                    System.out.println(chanel);
                }
            }
        } else {
            System.out.println("\n" + "Телевизор компании " + television.getCompanyName() + " с диагональю " + television.getDiagonalSize()
                    + " не включен");
        }
    }
}
