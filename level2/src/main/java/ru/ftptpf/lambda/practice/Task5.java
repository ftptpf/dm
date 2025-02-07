package ru.ftptpf.lambda.practice;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Дан класс Person с полями firstName, lastName, age.
 * Вывести полное имя самого старшего человека,
 * у которого длинна его имени не превышает 15 символов.
 */
public class Task5 {

    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Ivan", "Ivanov", 20),
                new Person("Petr", "Petrov", 25),
                new Person("Sergey", "Sergeev", 20),
                new Person("Sveta", "Svetikova", 45),
                new Person("Slava", "Slavikov", 18),
                new Person("Arni", "Kutuzov", 56)
        );
        persons.stream()
                .filter(person -> person.getFullName().length() <= 15)
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getFullName)
                .ifPresent(System.out::println);

        Map<Integer, List<Person>> map = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(map);
    }

}
