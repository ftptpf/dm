package ru.ftptpf.datetime.practice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Создать объект LocalDate, представляющий сегодняшнюю дату.
 * Преобразовать дату в строку вида 05.05.2020. Вывести эту строку на консоль.
 */
public class Task2 {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String string = localDate.format(formatter);
        System.out.println(string);
    }
}
