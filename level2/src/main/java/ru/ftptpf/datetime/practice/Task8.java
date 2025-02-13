package ru.ftptpf.datetime.practice;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Написать свою реализацию интерфейса TemporalAdjuster,
 * который бы прибавлял к дате 42 дня.
 */

public class Task8 {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.with(temporal -> temporal.plus(42, ChronoUnit.DAYS));
        System.out.println(localDateTime);
    }
}
