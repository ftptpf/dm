package ru.ftptpf.multithreading.practice;

import java.util.Queue;

public class ConsumerThread implements Runnable {

    private final Queue<Integer> list;

    public ConsumerThread(Queue<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) {
            while (true) {
                if (!list.isEmpty()) {
                    Integer removedValue = list.remove();
                    System.out.println("Consumer get value " + removedValue + ". List size " + list.size());
                } else {
                    System.out.println("List is empty. Consumer is waiting.");
                }
                try {
                    int random = RandomUtil.getRandom(80);
                    System.out.println("Consumer wait " + random + " ms");
                    list.notifyAll();
                    list.wait(random);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
