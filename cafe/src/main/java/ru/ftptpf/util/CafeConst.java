package ru.ftptpf.util;

/**
 * Константы приложения
 */
public final class CafeConst {

    // время, которое покупатель ждет следующий заказ
    public static final int BUYER_WAIT_TIME = 5;
    // максимальное количество товаров в заказе одного клиента
    public static final int MAX_PRODUCT_COUNT = 5;
    // время, которое тратит кассир на обработку одного товара
    public static final int PRODUCT_TIME_COST = 1;
    // как часто создается статистика по покупателя
    public static final int BUYER_STATS_JOB_PERIOD = 30;
    // как часто создается статистика по кассам
    public static final int CASHBOX_STATS_JOB_PERIOD = 60;
    // как часто создается итоговая статистика пол лучшим показателям
    public static final int WINNER_STATS_JOB_PERIOD = 90;

    private CafeConst() {
    }

}
