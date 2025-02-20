package ru.ftptpf.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        int value = atomicInteger.incrementAndGet();
        System.out.println(value);

        int newValue = atomicInteger.addAndGet(20);
        System.out.println(newValue);
    }
}
