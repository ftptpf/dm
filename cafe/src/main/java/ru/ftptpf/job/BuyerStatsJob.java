package ru.ftptpf.job;

import ru.ftptpf.csv.CsvRow;
import ru.ftptpf.mapper.BuyerMapper;
import ru.ftptpf.service.Buyer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

/**
 * Проходим по каждому покупателю и записываем в файл статистики
 */
public class BuyerStatsJob implements Runnable {

    private final BuyerMapper buyerMapper = new BuyerMapper();
    private final List<Buyer> buyers;
    private final Path bayerStatsPath;

    public BuyerStatsJob(List<Buyer> buyers, Path bayerStatsPath) {
        this.buyers = buyers;
        this.bayerStatsPath = bayerStatsPath;
    }

    @Override
    public void run() {
        try {
            List<String> csvRows = buyers.stream()
                    .map(buyerMapper::map)
                    .map(CsvRow::toCsvRow)
                    .toList();
            Files.write(bayerStatsPath, csvRows, CREATE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
