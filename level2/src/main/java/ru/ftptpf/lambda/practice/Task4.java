package ru.ftptpf.lambda.practice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Дан список целых чисел. Вывести строку, представляющую собой конкатенацию
 * строковых представлений этих чисел.
 */
public class Task4 {

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        String string = integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println(string);

    }
}
