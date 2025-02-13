package ru.ftptpf.datetime.practice;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Создать объект LocalDate представляющий сегодняшнюю дату.
 * Создать объект LocalDate представляющий дату "07.07.2018".
 * Найти количество дней между датами.
 */
public class Task5 {

    public static void main(String[] args) {
        LocalDate nowLocalDate = LocalDate.now();
        LocalDate parsedLocalDate = LocalDate.parse("07.07.2018", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Period period = Period.between(parsedLocalDate, nowLocalDate);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        long days = ChronoUnit.DAYS.between(parsedLocalDate, nowLocalDate);
        System.out.println(days);
    }
}
