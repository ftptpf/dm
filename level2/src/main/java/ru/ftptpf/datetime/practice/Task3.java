package ru.ftptpf.datetime.practice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Дана строка вида "26-03-1986T09:24" Получить объект LocalDateTime из этой строки.
 */
public class Task3 {

    public static void main(String[] args) {
        String textDate = "26-03-1986T09:24";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(textDate, formatter);
        System.out.println(localDateTime);
    }
}
