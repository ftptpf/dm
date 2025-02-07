package ru.ftptpf.lambda;

import java.util.stream.Stream;

/**
 * Из трех миллионов студентов ищем одного с максимальным возрастом.
 * Для этого разбиваем на три группы по миллиону каждая.
 * Каждая из групп будет обрабатываться параллельно.
 */
public class MapReduceExample {

    public static void main(String[] args) {
        Stream.of(
                        new Student(18, "Ivan"),
                        new Student(20, "Petr"),
                        new Student(34, "Vasya"),
                        new Student(45, "Sveta"),
                        new Student(101, "Katya"),
                        new Student(69, "Denis"),
                        new Student(24, "Kira")
                )
                .parallel()
                .map(Student::getAge)
                .reduce(Math::max)
                .ifPresent(System.out::println);


    }
}
