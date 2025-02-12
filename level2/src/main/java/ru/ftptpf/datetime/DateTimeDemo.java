package ru.ftptpf.datetime;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeDemo {

    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        long epochMilli = now.toInstant().toEpochMilli();
        System.out.println(epochMilli);

        ZonedDateTime plus = now.plus(1L, ChronoUnit.DAYS);
        System.out.println(plus);

        ZonedDateTime truncated = now.truncatedTo(ChronoUnit.DAYS);
        System.out.println(truncated);
    }
}
