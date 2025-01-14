package ru.ftptpf.service;

import ru.ftptpf.model.Order;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.IntStream;

/**
 * Сервис работы кафе
 */
public class Cafe extends Thread {

    private final ScheduledExecutorService executorService;
    private final List<Buyer> buyers;
    private final List<Cashbox> cashboxes;
    private final BlockingQueue<Order> allOrders;

    public Cafe(int buyerNumbers, int cashboxNumbers) {
        this.executorService = Executors.newScheduledThreadPool(3);
        this.buyers = createBuyers(buyerNumbers);
        this.cashboxes = createCashboxes(cashboxNumbers);
        this.allOrders = new ArrayBlockingQueue<>(cashboxNumbers * 10);
    }

    /**
     * Для каждого покупателя и каждой кассы запускаем свой поток
     */
    @Override
    public void run() {
        buyers.forEach(buyer -> new Thread(buyer).start());
        cashboxes.forEach(cashbox -> new Thread(cashbox).start());
    }

    private List<Buyer> createBuyers(int buyerNumbers) {
        return IntStream.range(0, buyerNumbers)
                .mapToObj(i -> new Buyer(allOrders))
                .toList();
    }

    private List<Cashbox> createCashboxes(int cashboxNumbers) {
        return IntStream.range(0, cashboxNumbers)
                .mapToObj(i -> new Cashbox(allOrders))
                .toList();
    }
}
