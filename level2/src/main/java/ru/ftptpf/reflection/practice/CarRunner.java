package ru.ftptpf.reflection.practice;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Создать класс Car с полями String brand, String model.
 * Создать аннотацию @Table (принимает название схемы и таблицы в базе данных),
 * и @Column (принимает название колонки в таблице в базе данных).
 * Пометить класс аннотацией @Table, поля - аннотациями @Column.
 * Написать программу, принимающую объект класса Car с проинициализированными полями и составляющую запрос INSERT
 * в виде строги на основании данных объекта.
 * Пример: Car car = new Car("Toyota", "Corolla");
 * Программа, принимающая этот объект, должна вывести на консоль строку:
 * "INSERT INTO garage.car (brand, model) VALUES ('Toyota', 'Corolla')"
 */
public class CarRunner {

    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla");
        System.out.println(car);
        System.out.println(generateInsert(car));
    }

    private static String generateInsert(Car car) {
        String template = "INSERT INTO %s.%s (%s) VALUES (%s);";
        Table table = car.getClass().getAnnotation(Table.class);
        Field[] fields = car.getClass().getDeclaredFields();

        String fieldNames = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .sorted(Comparator.comparing(Field::getName))
                .map(field -> field.getAnnotation(Column.class))
                .map(Column::name)
                .collect(Collectors.joining(","));

        String fieldValues = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .sorted(Comparator.comparing(Field::getName))
                .peek(field -> field.setAccessible(true))
                .map(field -> {
                    try {
                        return String.valueOf(field.get(car));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .map(value -> "'" + value + "'")
                .collect(Collectors.joining(","));

        return String.format(template, table.schema(), table.value(), fieldNames, fieldValues);
    }
}
