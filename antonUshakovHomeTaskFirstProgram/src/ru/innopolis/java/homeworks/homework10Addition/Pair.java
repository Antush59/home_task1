package ru.innopolis.java.homeworks.homework10Addition;

import java.util.Objects;

public class Pair<T, K> {

    private final T valueT;
    private final K valueK;

    private Pair(T valueT, K valueK) {
        this.valueT = valueT;
        this.valueK = valueK;
    }

    public static <T, K> Pair<T, K> of(T valueT, K valueK) {
        return new Pair<>(valueT, valueK);
    }

    public T getFirst() {
        return valueT;
    }

    public K getSecond() {
        return valueK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(valueT, pair.valueT) && Objects.equals(valueK, pair.valueK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueT, valueK);
    }
}
