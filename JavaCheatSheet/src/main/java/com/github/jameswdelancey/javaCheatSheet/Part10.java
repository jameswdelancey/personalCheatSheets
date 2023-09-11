package com.github.jameswdelancey.javaCheatSheet;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class Part10 {
    public static int doubleValue(int n) {
        return n * 2;
    }

    public static int tripleValue(int n) {
        return n * 3;
    }

    public static IntUnaryOperator makeMultiplierOld(int x) {
        switch (x) {
            case 2:
                return Part10::doubleValue;
            case 3:
                return Part10::tripleValue;
            default:
                throw new UnsupportedOperationException("Not a valid multiplier");
        }
    }

    public static IntUnaryOperator makeMultiplier(int x) {
        return n -> n * x;
    }

    public static Function<Integer, Integer> makeMultiplier2(int[] x) {
        int[] counter = { x[0] };
        return n -> {
            counter[0]++;
            return n * counter[0];
        };
    }

    public static Function<Integer, Integer> makeMultiplier3(int[] x) {
        int[] counter = { x[0] };
        return n -> {
            x[0] = counter[0];
            counter[0]++;
            return n * x[0];
        };
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };

        IntStream multiplied = Arrays.stream(nums).map(makeMultiplierOld(2));
        multiplied.forEach(System.out::println);

        IntStream multiplied2 = Arrays.stream(nums).map(n -> n * 3);
        multiplied2.forEach(System.out::println);

        IntStream multiplied3 = Arrays.stream(nums).map(makeMultiplier(3));
        multiplied3.forEach(System.out::println);
    }
}
