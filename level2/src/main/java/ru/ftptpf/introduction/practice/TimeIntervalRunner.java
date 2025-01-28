package ru.ftptpf.introduction.practice;

/**
 * Создать класс описывающий промежуток времени.
 * Сам промежуток времени должен задаваться тремя свойствами:
 * секундами, минутами и часами.
 * Создать такой метод для получения полного количества секунд в объекте.
 * Создать два конструктора: первый принимает общее количество секунд,
 * второй часы, минуты и секунды по отдельности.
 * Создать метод для вывода данных.
 */
public class TimeIntervalRunner {

    public static void main(String[] args) {
        TimeInterval timeInterval = new TimeInterval(30, 2, 0);
        System.out.println(timeInterval.totalSeconds());

        TimeInterval timeInterval2 = new TimeInterval(timeInterval.totalSeconds());
        System.out.println(timeInterval2.totalSeconds());
        timeInterval2.print();

        timeInterval.sum(timeInterval2).print();
    }

}
