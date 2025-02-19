package ru.ftptpf.multithreading.practice;

import java.util.Queue;

public class ProducerThread implements Runnable {

    private final Queue<Integer> list;

    public ProducerThread(Queue<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) {
            while (true) {
                if (list.size() < 10) {
                    int random = RandomUtil.getRandom();
                    list.add(random);
                    System.out.println("Producer added value " + random + ". List size " + list.size());
                } else {
                    System.out.println("---------------- List is full. Producer can't add.");
                }
                list.notifyAll();
                try {
                    int randomWaitValue = RandomUtil.getRandom();
                    System.out.println("Producer is waiting " + randomWaitValue + " ms");
                    list.wait(randomWaitValue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
