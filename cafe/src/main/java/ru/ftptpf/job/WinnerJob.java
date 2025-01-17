package ru.ftptpf.job;

import ru.ftptpf.csv.CsvRow;
import ru.ftptpf.dto.BuyerRow;
import ru.ftptpf.dto.CashboxRow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Comparator.comparing;

public class WinnerJob implements Runnable {

    private final Path bayerStatsPath;
    private final Path cashboxStatsPath;

    public WinnerJob(Path bayerStatsPath, Path cashboxStatsPath) {
        this.bayerStatsPath = bayerStatsPath;
        this.cashboxStatsPath = cashboxStatsPath;
    }

    @Override
    public void run() {
        try {
            determineBuyerWinner();
            determineCashboxWinner();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void determineBuyerWinner() throws IOException {
        Files.readAllLines(bayerStatsPath).stream()
                .map(line -> line.split(CsvRow.COLUMN_SEPARATOR))
                .map(BuyerRow::new)
                .max(comparing(buyerRow -> buyerRow.orderNumbers() * buyerRow.orderPRiceAvg()))
                .ifPresent(buyerRow -> System.out.println("Buyer winner " + buyerRow.id()));
    }

    private void determineCashboxWinner() throws IOException {
        Files.readAllLines(cashboxStatsPath).stream()
                .map(line -> line.split(CsvRow.COLUMN_SEPARATOR))
                .map(CashboxRow::new)
                .max(comparing(cashboxRow -> cashboxRow.orderPriceSum() / cashboxRow.orderNumbers()))
                .ifPresent(cashboxRow -> System.out.println("Cashbox winner " + cashboxRow.id()));
    }
}
