package ru.ftptpf.mapper;

import ru.ftptpf.dto.BuyerRow;
import ru.ftptpf.model.Order;
import ru.ftptpf.model.Product;
import ru.ftptpf.service.Buyer;

import java.util.List;

public class BuyerMapper implements Mapper<Buyer, BuyerRow> {

    @Override
    public BuyerRow map(Buyer buyer) {
        return new BuyerRow(
                buyer.getId(),
                buyer.getOrders().size(),
                getCaloriesAvg(buyer.getOrders()),
                getOrderPriceAvg(buyer.getOrders())
        );
    }

    private double getCaloriesAvg(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.products().stream())
                .mapToInt(Product::calories)
                .average()
                .orElse(0.0);
    }

    private double getOrderPriceAvg(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.products().stream())
                .mapToInt(Product::price)
                .average()
                .orElse(0.0);
    }
}
