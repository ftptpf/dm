package ru.ftptpf.lambda.practice;

import java.util.Map;

/**
 * Дана Map<String, Integer>. Найти сумму всех значений,
 * длина ключей которых меньше 7 символов.
 */
public class Task3 {

    public static void main(String[] args) {
        Map<String, Integer> map = Map.of(
                "string1", 1,
                "str2", 2,
                "string3", 3,
                "string4", 4,
                "strin5", 5
        );
        int sum = map.entrySet().stream()
                .filter(entry -> entry.getKey().length() < 7)
                .mapToInt(Map.Entry::getValue)
                .sum();
        System.out.println(sum);
    }
}
