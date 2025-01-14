package ru.ftptpf.service;

import ru.ftptpf.model.Order;
import ru.ftptpf.model.Product;
import ru.ftptpf.model.ProductFactory;
import ru.ftptpf.model.ProductType;
import ru.ftptpf.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

import static ru.ftptpf.util.CafeConst.BUYER_WAIT_TIME;
import static ru.ftptpf.util.CafeConst.MAX_PRODUCT_COUNT;

/**
 * Сервис покупателя
 */
public class Buyer implements Runnable {

    private static int idGenerator = 1;

    private final int id;
    private final List<Order> orders = new ArrayList<>();
    private final BlockingQueue<Order> allOrders;

    public Buyer(BlockingQueue<Order> allOrders) {
        this.id = idGenerator++;
        this.allOrders = allOrders;
    }

    @Override
    public void run() {
        while (true) {
            try {
                var productsNumber = RandomUtil.get(MAX_PRODUCT_COUNT);
                List<Product> products = IntStream.range(0, productsNumber)
                        .mapToObj(i -> getRandomProduct())
                        .toList();
                Order order = new Order(id, products);

                allOrders.put(order);
                orders.add(order);

                Thread.sleep(BUYER_WAIT_TIME * 1000L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    private Product getRandomProduct() {
        var types = ProductType.values();
        var randomIndex = RandomUtil.get(types.length);
        return ProductFactory.get(types[randomIndex]);
    }


    public int getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
