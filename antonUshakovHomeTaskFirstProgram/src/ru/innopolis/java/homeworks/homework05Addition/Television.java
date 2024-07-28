package ru.innopolis.java.homeworks.homework05Addition;

import java.util.List;
import java.util.Objects;

public class Television {

    private String companyName;
    private boolean tvIsOn;
    private Integer diagonalSize;
    private Integer numberChannel;
    private Integer volume;
    private final Integer MIN_VOLUME = 20;
    private final Integer MAX_VOLUME = 80;
    private final Integer MIN_CHANNEL = 0;
    private List<Channel> channelList;

    public Television(String companyName, int diagonalSize, List<Channel> channelList) {
        this.companyName = companyName;
        this.diagonalSize = diagonalSize;
        this.channelList = channelList;
    }

    //Включение телевизора.
    public void pushPowerButton(boolean pwButton) {
        tvIsOn = pwButton;
    }

    //Выбор канала от 1 до 400. Исключен выбор канала, если телевизор выключен.
    //Исключен выбор канала больше установленного количества каналов  и ниже 0.
    public boolean setChannel(Integer numberChanel, int countChanel) {
        if (numberChanel <= countChanel && numberChanel > MIN_CHANNEL) {
            this.numberChannel = numberChanel;
            return true;
        } else {
            System.out.println("Выберите канал от 1 до " + countChanel);
            return false;
        }
    }

    //    Исключаем из выбора громкости уровень ниже 20 и выше 80
    public boolean setVolume(Integer volume) {
        if (volume <= MAX_VOLUME && volume >= MIN_VOLUME) {
            this.volume = volume;
            return true;
        } else {
            System.out.println("Выберите громкость в дапазоне от 20 до 80");
            return false;
        }
    }

    public List<Channel> getChannelList() {
        return channelList;
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

    public Integer getNumberChannel() {
        return numberChannel;
    }

    public Integer getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Television{" +
                "companyName='" + companyName + '\'' +
                ", diagonalSize=" + diagonalSize +
                ", tvIsOn=" + tvIsOn +
                ", numberChannel=" + numberChannel +
                ", maxVolume=" + volume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Television that = (Television) o;
        return tvIsOn == that.tvIsOn && Objects.equals(companyName, that.companyName) && Objects.equals(diagonalSize, that.diagonalSize) && Objects.equals(numberChannel, that.numberChannel) && Objects.equals(volume, that.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, tvIsOn, diagonalSize, numberChannel, volume);
    }

    public void printTV(Television television) {
        if (television.isTvIsOn()) {
            System.out.println("\n" + "Информация о телевизоре:");
            System.out.println("Телевизор компании " + television.getCompanyName() + " с диагональю " + television.getDiagonalSize()
                    + " включен, " + " выставлена громкость " + television.getVolume() + "%, " + "включен " + television.getNumberChannel() + "канал");
            for (Channel channel : television.getChannelList()) {
                if (Objects.equals(numberChannel, channel.getNumber())) {
                    System.out.println("О канале:");
                    System.out.println(channel);
                }
            }
        } else {
            System.out.println("\n" + "Телевизор компании " + television.getCompanyName() + " с диагональю " + television.getDiagonalSize()
                    + " не включен");
        }
    }
}
