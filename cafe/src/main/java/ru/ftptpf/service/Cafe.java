package ru.ftptpf.service;

import ru.ftptpf.job.BuyerStatsJob;
import ru.ftptpf.job.CashboxStatsJob;
import ru.ftptpf.job.WinnerJob;
import ru.ftptpf.model.Order;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;
import static ru.ftptpf.util.CafeConst.*;

/**
 * Сервис работы кафе
 */
public class Cafe extends Thread {

    private final ScheduledExecutorService executorService;
    private final List<Buyer> buyers;
    private final List<Cashbox> cashboxes;
    private final BlockingQueue<Order> allOrders;

    public Cafe(int buyerNumbers, int cashboxNumbers) {
        this.executorService = Executors.newScheduledThreadPool(3);
        this.allOrders = new ArrayBlockingQueue<>(cashboxNumbers * 10);
        this.buyers = createBuyers(buyerNumbers);
        this.cashboxes = createCashboxes(cashboxNumbers);
        initShutdownHook();
    }

    /**
     * Для каждого покупателя и каждой кассы запускаем свой поток.
     * Так же запускаем процессы для формирования отчетов.
     */
    @Override
    public void run() {
        buyers.forEach(buyer -> new Thread(buyer).start());
        cashboxes.forEach(cashbox -> new Thread(cashbox).start());

        Path bayerStatsPath = Path.of("resources", "buyers-stats.csv");
        executorService.scheduleAtFixedRate(
                new BuyerStatsJob(buyers, bayerStatsPath),
                BUYER_STATS_JOB_PERIOD,
                BUYER_STATS_JOB_PERIOD,
                SECONDS);

        Path cashboxStatsPath = Path.of("resources", "cashboxes-stats.csv");
        executorService.scheduleAtFixedRate(
                new CashboxStatsJob(cashboxes, cashboxStatsPath),
                CASHBOX_STATS_JOB_PERIOD,
                CASHBOX_STATS_JOB_PERIOD,
                SECONDS);

        executorService.scheduleAtFixedRate(
                new WinnerJob(bayerStatsPath, cashboxStatsPath),
                WINNER_STATS_JOB_PERIOD,
                WINNER_STATS_JOB_PERIOD,
                SECONDS);
    }

    private void initShutdownHook() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    System.out.println("Shutdown initiated...");
                    executorService.shutdown();
                    System.out.println("Shutdown finished successfully");
                }));
    }

    private List<Buyer> createBuyers(int buyerNumbers) {
        List<Buyer> buyers = new ArrayList<>();
        for (int i = 0; i < buyerNumbers; i++) {
            buyers.add(new Buyer(allOrders));
        }
        return buyers;
    }

    private List<Cashbox> createCashboxes(int cashboxNumbers) {
        List<Cashbox> cashboxes = new ArrayList<>();
        for (int i = 0; i < cashboxNumbers; i++) {
            cashboxes.add(new Cashbox(allOrders));
        }
        return cashboxes;
    }
}
