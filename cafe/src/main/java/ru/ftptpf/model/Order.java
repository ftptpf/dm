package ru.ftptpf.model;

import java.util.List;

public record Order(int buyerId,
                    List<Product> products) {
}
