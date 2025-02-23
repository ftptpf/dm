package ru.ftptpf.concurrent.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RocketDetailRunnable implements Runnable {

    private final RocketDetail rocketDetail;
    private final CyclicBarrier cyclicBarrier;

    public RocketDetailRunnable(RocketDetail rocketDetail, CyclicBarrier cyclicBarrier) {
        this.rocketDetail = rocketDetail;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("Готовится деталь: " + rocketDetail);
        try {
            Thread.sleep(1000L);
            System.out.println("Деталь готова и ожидает: " + rocketDetail);
            // Пока cyclicBarrier.await() не вызовет то количество потоков которое было передано при создании CyclicBarrier,
            // программа не пойдет дальше. Тогда все потоки продолжат свое выполнение.
            cyclicBarrier.await();
            System.out.println("Деталь использована: " + rocketDetail);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
