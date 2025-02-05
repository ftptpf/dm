package ru.ftptpf.collections.practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Написать метод isUnique, который принимает Map<String, String>
 * и возвращает true, если каждому ключу соответствует свое уникальное значение.
 */
public class Task3 {

    public static void main(String[] args) {

/*        Map<String, String> map1 = new HashMap<>();
        map1.put("Marty", "Stepp");
        map1.put("Stuart", "Reges");*/

        Map<String, String> map1 = Map.of(
                "Marty", "Stepp",
                "Stuart", "Reges");
        Map<String, String> map2 = Map.of(
                "Marty", "Stepp",
                "Stuart", "Stepp");

        System.out.println(isUnique(map1));
        System.out.println(isUnique(map2));

    }

    public static boolean isUnique(Map<String, String> map) {
        return map.size() == new HashSet<>(map.values()).size();
    }
}
