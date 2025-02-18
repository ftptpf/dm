package ru.ftptpf.multithreading.queue;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CashboxRunner {

    public static void main(String[] args) throws InterruptedException {
        Queue<Cashbox> cashboxes = new ArrayDeque<>(List.of(new Cashbox(), new Cashbox()));
        List<Thread> traders = Stream.of(
                        new BuyerThread(cashboxes),
                        new BuyerThread(cashboxes),
                        new BuyerThread(cashboxes),
                        new BuyerThread(cashboxes),
                        new BuyerThread(cashboxes),
                        new BuyerThread(cashboxes),
                        new BuyerThread(cashboxes),
                        new BuyerThread(cashboxes)
                )
                .map(Thread::new)
                .peek(Thread::start)
                .collect(Collectors.toList());

        for (Thread trader : traders) {
            trader.join();
        }
    }
}
