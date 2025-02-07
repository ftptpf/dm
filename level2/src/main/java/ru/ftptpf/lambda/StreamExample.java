package ru.ftptpf.lambda;

import java.util.Comparator;
import java.util.List;

public class StreamExample {

    public static void main(String[] args) {
        List<String> strings = List.of("88", "11", "22", "33", "44", "55", "66", "77", "88");

/*        for (String string : strings) {
            String value = string + string;
            Integer intValue = Integer.parseInt(value);
            if (intValue % 2 == 0) {
                System.out.println(intValue);
            }
        }*/

        strings.stream()
                .map(string -> string + string)
                .map(Integer::parseInt)
                .filter(integer -> integer % 2 == 0)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .limit(2)
                .forEach(System.out::println);
    }
}
