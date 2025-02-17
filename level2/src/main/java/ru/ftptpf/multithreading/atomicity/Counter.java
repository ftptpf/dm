package ru.ftptpf.multithreading.atomicity;

/**
 * Создать класс Counter с одним полем count.
 */
public class Counter {

    private int count;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
