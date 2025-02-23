package ru.ftptpf.concurrent.barrier;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(RocketDetail.values().length, () -> System.out.println("Пуск!!"));

        ExecutorService executorService = Executors.newCachedThreadPool();

        Arrays.stream(RocketDetail.values())
                .map(detail -> new RocketDetailRunnable(detail, cyclicBarrier))
                .forEach(executorService::submit);

        executorService.shutdown();
        try {
            executorService.awaitTermination(1L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
