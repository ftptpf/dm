package ru.ftptpf.model;

import java.util.List;

public record Order(Integer buyerId,
                    List<Product> products) {
}
