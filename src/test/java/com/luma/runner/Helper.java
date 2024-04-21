package com.luma.runner;

import java.util.List;
import java.util.stream.IntStream;

public class Helper {

    public static double getMax(List<Double> list) {
        return list.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(Double.MIN_VALUE);
    }

    public static boolean isDescending(List<Double> list) {
        return IntStream
                .range(1, list.size())
                .allMatch(i -> list.get(i) <= list.get(i - 1));
    }

    public static double getMin(List<Double> list) {
        return list.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(Double.MAX_VALUE);
    }

    public static boolean isAscending(List<Double> list) {
        return IntStream.range(0, list.size() - 1)
                .allMatch(i -> list.get(i) <= list.get(i + 1));
    }
}
