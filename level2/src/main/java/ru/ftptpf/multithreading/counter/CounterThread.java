package ru.ftptpf.multithreading.counter;

/**
 * Создать класс CounterThread с одним полем и конструктором для инициализации:
 * private Counter counter
 * В методе run этого класса 100 раз вызывать метод increment
 */
public class CounterThread extends Thread {

    private final Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}
