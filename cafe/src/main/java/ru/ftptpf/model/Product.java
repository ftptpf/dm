package ru.ftptpf.model;

public record Product(ProductType type,
                      int calories,
                      int price) {
}
