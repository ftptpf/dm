package ru.ftptpf.multithreading.practice;

import java.util.Random;

public final class RandomUtil {

    private static final Random RANDOM = new Random();
    private static final int DEFAULT_MAX = 10;

    private RandomUtil() {
    }

    public static int getRandom() {
        return RANDOM.nextInt(DEFAULT_MAX);
    }

    public static int getRandom(int max) {
        return RANDOM.nextInt(max);
    }
}
