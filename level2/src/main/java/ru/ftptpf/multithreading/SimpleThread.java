package ru.ftptpf.multithreading;

public class SimpleThread extends Thread {

    @Override
    public void run() {
//        System.out.println("Hello " + Thread.currentThread().getName() + "!");
        System.out.println("Hello " + getName() + "!");
    }
}
