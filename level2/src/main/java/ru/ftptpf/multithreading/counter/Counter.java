package ru.ftptpf.multithreading.counter;

/**
 * Создать класс Counter с одним полем count.
 */
public class Counter {

    private static String description;
    private int count;

    /**
     * Статические поля синхронизируются по классу
     */
    public static void init() {
        synchronized (Counter.class) {
            if (description == null) {
                description = "Test description";
            }
        }
    }

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public synchronized void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
