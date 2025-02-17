package ru.ftptpf.multithreading.counter;

/**
 * В методе main создать 4 потока типа CounterThread,
 * передав один и тот же объект Counter и запустить их.
 * Дождаться выполнения и вывести на консоль текущее значение счетчика counter.getCount().
 */
public class CounterDemo {

    /**
     * count++
     * 1 считывание
     * 2 изменение
     * 3 запись
     */
    public static void main(String[] args) {
        Counter counter = new Counter();
        var counterThread1 = new CounterThread(counter);
        var counterThread2 = new CounterThread(counter);
        var counterThread3 = new CounterThread(counter);
        var counterThread4 = new CounterThread(counter);
        var counterThread5 = new CounterThread(counter);
        var counterThread6 = new CounterThread(counter);
        var counterThread7 = new CounterThread(counter);
        var counterThread8 = new CounterThread(counter);

        counterThread1.start();
        counterThread2.start();
        counterThread3.start();
        counterThread4.start();
        counterThread5.start();
        counterThread6.start();
        counterThread7.start();
        counterThread8.start();

        try {
            counterThread1.join();
            counterThread2.join();
            counterThread3.join();
            counterThread4.join();
            counterThread5.join();
            counterThread6.join();
            counterThread7.join();
            counterThread8.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getCount());
    }
}
