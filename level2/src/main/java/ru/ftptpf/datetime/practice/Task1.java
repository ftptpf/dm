package ru.ftptpf.datetime.practice;

import java.time.LocalDateTime;

/**
 * Создать объект LocalDateTime, представляющий собой дату в формате 25.08.2020 19:47.
 * Используя этот объект создать другой объект LocalDateTime, представляющий дату через три месяца
 * после приведенной. Вывести на консоль содержащиеся нем объекты LocalDate и LocalTime.
 */
public class Task1 {

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.of(2020, 8, 25, 19, 47);
        LocalDateTime plussedLocalDateTime = localDateTime.plusMonths(3L);
        System.out.println(localDateTime);
        System.out.println(plussedLocalDateTime);
        System.out.println(plussedLocalDateTime.toLocalDate());
        System.out.println(plussedLocalDateTime.toLocalTime());
    }
}
