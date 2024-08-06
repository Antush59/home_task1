package ru.innopolis.java.homeworks.homework10Addition;

import java.util.Objects;

public class Pair<T, K> {

    private final T first;
    private final K second;

    private Pair(T valueT, K valueK) {
        this.first = valueT;
        this.second = valueK;
    }

    public static <T, K> Pair<T, K> of(T valueT, K valueK) {
        return new Pair<>(valueT, valueK);
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
