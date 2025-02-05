package ru.ftptpf.collections.practice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Написать метод countUnique, который принимает целочисленный список и возвращает
 * количество уникальных элементов в списке.
 * При получении пустого списка метод должен возвращать 0.
 */
public class Task2 {

    public static void main(String[] args) {

        List<Integer> list = List.of(3, 7, 3, -1, 2, 3, 7);
        int result = countUnique(list);
        System.out.println(result);

    }

    public static int countUnique(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        return set.size();
    }
}
