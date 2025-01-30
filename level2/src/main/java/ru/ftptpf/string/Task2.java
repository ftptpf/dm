package ru.ftptpf.string;

/**
 * Написать функцию, принимающую 2 параметра: строку и слово
 * и возвращающую true если строка начинается и заканчивается этим словом.
 */
public class Task2 {
    public static void main(String[] args) {
        String value = "hello word hello";
        String word = "hello";
        System.out.println(isStartAndEnd(value, word));
    }

    private static boolean isStartAndEnd(String value, String word) {
        return value.startsWith(word) && value.endsWith(word);
    }
}
