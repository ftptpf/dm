package ru.ftptpf.service;

import ru.ftptpf.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static ru.ftptpf.util.CafeConst.PRODUCT_TIME_COST;

/**
 * Сервис работы кассы
 */
public class Cashbox implements Runnable {

    private static int igGenerator = 1;

    private final int id;
    private final List<Order> orders = new ArrayList<>();
    private final BlockingQueue<Order> allOrders;

    public Cashbox(BlockingQueue<Order> allOrders) {
        this.id = igGenerator++;
        this.allOrders = allOrders;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Order order = allOrders.take();
                Thread.sleep(order.products().size() * PRODUCT_TIME_COST * 1000L);
                orders.add(order);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
