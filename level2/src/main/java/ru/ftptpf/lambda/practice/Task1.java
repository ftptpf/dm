package ru.ftptpf.lambda.practice;

import java.util.List;

/**
 * Дан список целых чисел. Найти среднее всех нечетных чисел, делящихся на 5.
 */
public class Task1 {

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 3, 4, 6, 8, 5, 15, 20, 25, 10);
        integers.stream()
                .filter(integer -> integer % 2 != 0 && integer % 5 == 0)
                .mapToInt(Integer::intValue)
                .average()
                .ifPresent(System.out::println);
    }
}
