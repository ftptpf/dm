package ru.ftptpf.mapper;

import ru.ftptpf.dto.CashboxRow;
import ru.ftptpf.model.Order;
import ru.ftptpf.model.Product;
import ru.ftptpf.service.Cashbox;

import java.util.List;

public class CashboxMapper implements Mapper<Cashbox, CashboxRow> {

    @Override
    public CashboxRow map(Cashbox cashbox) {
        return new CashboxRow(
                cashbox.getId(),
                cashbox.getOrders().size(),
                getOrderPriceSum(cashbox.getOrders())
        );
    }

    private int getOrderPriceSum(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.products().stream())
                .mapToInt(Product::price)
                .sum();
    }
}
