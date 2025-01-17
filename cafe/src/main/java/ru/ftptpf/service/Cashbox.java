package ru.ftptpf.service;

import ru.ftptpf.model.Order;
import ru.ftptpf.util.CafeConst;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;


/**
 * Сервис работы кассы
 */
public class Cashbox implements Runnable {

    private static int igGenerator = 1;

    private final Integer id;
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
                Thread.sleep(order.products().size() * CafeConst.PRODUCT_TIME_COST * 1000L);
                orders.add(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
