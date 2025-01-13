package ru.ftptpf.model;

import static ru.ftptpf.model.ProductType.*;

/**
 * Класс для создания продуктов
 */
public final class ProductFactory {

    private ProductFactory() {
    }

    public static Product get(ProductType type) {
        return switch (type) {
            case STEAK -> new Product(STEAK, 500, 10);
            case SALAD -> new Product(SALAD, 50, 5);
            case POTATO -> new Product(POTATO, 300, 3);
            case COLA -> new Product(COLA, 25, 2);
            case ICE_CREAM -> new Product(ICE_CREAM, 150, 4);
        };
    }
}
