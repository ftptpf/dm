package ru.ftptpf.lambda.practice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Дан список строк. Найти количество уникальных строк длинной более 8 символов.
 */
public class Task2 {

    public static void main(String[] args) {
        List<String> strings = List.of(
                "string-1",
                "string-2",
                "string-3",
                "string-4",
                "string-10",
                "string-10",
                "string-10",
                "string-12",
                "string-16"
        );
        int result1 = strings.stream()
                .filter(string -> string.length() > 8)
                .collect(Collectors.toSet())
                .size();
        System.out.println(result1);

        long result2 = strings.stream()
                .filter(string -> string.length() > 8)
                .distinct()
                .count();
        System.out.println(result2);
    }
}
