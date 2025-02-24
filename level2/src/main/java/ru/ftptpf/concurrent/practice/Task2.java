package ru.ftptpf.concurrent.practice;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Написать программу бесконечно считывающую пользовательские исла их консоли.
 * Эти числа представляют собой количество секунд.
 * При каждом вводе числа должна создаваться задача, которая засыпает на введенное число секунд
 * а затем выводит "Я спал N секунд".
 * Однако нужно сделать так, чтобы все задачи выполнялись в одном потоке в порядке ввода.
 * Т.е. в программе есть 2 потока: главный и поток выполнения всех задач по очереди.
 * При вводе отрицательного числа программа должна завершаться.
 * Второе решение несколько потоков в пуле. Посчитать количество обработанных задач каждым потоком.
 */
public class Task2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int seconds = scanner.nextInt();
            if (seconds < 0) {
                break;
            }
            executorService.submit(() -> {
                Integer counter = threadLocal.get();
                threadLocal.set(counter == null ? 1 : ++counter);
                System.out.printf("Поток %s обработал %d задач", Thread.currentThread().getName(), threadLocal.get());
                System.out.println();
                Thread.sleep(seconds * 1000L);
                System.out.printf("Поток %s спал %d секунд%n", Thread.currentThread().getName(), seconds);
                return seconds;
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
