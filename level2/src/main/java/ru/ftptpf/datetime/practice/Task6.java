package ru.ftptpf.datetime.practice;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Даны два объекта LocalDate из Task5.
 * Посчитать количество секунд между полуночью первой и второй даты.
 */
public class Task6 {

    public static void main(String[] args) {
        LocalDate nowLocalDate = LocalDate.now();
        LocalDate parsedLocalDate = LocalDate.of(2018, 7, 7);

        /*        LocalDateTime.of(nowLocalDate, LocalTime.MIDNIGHT);*/
        LocalDateTime localDateTimeNow = nowLocalDate.atStartOfDay();
        LocalDateTime localDateTimePrev = parsedLocalDate.atStartOfDay();

        Duration duration = Duration.between(localDateTimePrev, localDateTimeNow);
        System.out.println(duration.getSeconds());
    }
}
