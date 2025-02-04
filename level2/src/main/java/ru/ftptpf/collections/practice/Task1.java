package ru.ftptpf.collections.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Написать метод, который принимает целочисленный список
 * и возвращает список только с нечетными значениями.
 */
public class Task1 {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> result = removeEven(list);
        System.out.println(result);
    }

    /**
     * Параметры, которые передаются в метод лучше не изменять.
     *
     * @param list
     * @return
     */
    private static List<Integer> removeEven(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Integer value : list) {
            if (value % 2 != 0) {
                result.add(value);
            }
        }
        return result;
    }
}
