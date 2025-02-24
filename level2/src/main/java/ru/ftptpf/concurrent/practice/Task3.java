package ru.ftptpf.concurrent.practice;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Задан массив случайных числ от 1 до 300, случайной длины до 1 млн элементов.
 * Найти максимальный элемент в массиве двумя способами:
 * в одном потоке и используя 10 потоков.
 * Сравнить затраченное в обоих случаях время.
 */
public class Task3 {

    public static void main(String[] args) {
        int[] array = new int[100_000_000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(300) + 1;
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        tracTime(() -> findMax(array));
        tracTime(() -> {
            try {
                return findMaxParallel(array, threadPool);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static int tracTime(Supplier<Integer> supplier) {
        long startTimeMillis = System.currentTimeMillis();
        int result = supplier.get();
        System.out.println(result + " : " + (System.currentTimeMillis() - startTimeMillis));
        return result;
    }

    private static int findMax(int[] array) {
        return IntStream.of(array)
                .max()
                .orElse(-1);
    }

    private static int findMaxParallel(int[] array, ExecutorService executorService) throws ExecutionException, InterruptedException {
        return executorService.submit(
                        () -> IntStream.of(array)
                                .parallel()
                                .max()
                                .orElse(-1))
                .get();
    }
}
