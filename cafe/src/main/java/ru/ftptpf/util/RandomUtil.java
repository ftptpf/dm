package ru.ftptpf.util;

import java.util.Random;

/**
 * Класс для генерации случайных чисел от нуля до переданного числа
 */
public final class RandomUtil {

    private static final Random RANDOM = new Random();

    private RandomUtil() {
    }

    public static int get(int max) {
        return RANDOM.nextInt(max);
    }
}
