package ru.ftptpf.multithreading;

public class SimpleRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello from Runnable" + Thread.currentThread().getName() + "!");

    }
}
