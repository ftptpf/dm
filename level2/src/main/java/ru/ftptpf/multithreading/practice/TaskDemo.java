package ru.ftptpf.multithreading.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Задан LinkedList представляющий собой хранилище целых чисел.
 * Поток ProducerThread с какой-то периодичностью бесконечно добавляет в этот список случайные целые числа.
 * Однако максимальное количество добавляемых чисел равно 10.
 * Поток ConsumerThread с какой-то периодичностью бесконечно извлекает из этого списка случайные целые числа
 * с задержкой от 1 до 10 мс.
 * <p>
 * Сделать так, чтобы метод produce добавлял числа только тогда когда не превышен лимит,
 * а метод consume извлекал только тогда когда в списке что-нибудь есть.
 * При этом методы должны корректно работать в многопоточной среде.
 * <p>
 * Создать и запустить два потока. Один из которых вызывает producer, второй consumer.
 * Продемонстрировать корректность работы хранилища с помощью вывода сообщений на консоль о добавлении,
 * получении и текущем размере хранилища на этапах добавления и извлечения.
 */
public class TaskDemo {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        Thread producerThread = new Thread(new ProducerThread(queue));
        Thread consumerThread = new Thread(new ConsumerThread(queue));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
