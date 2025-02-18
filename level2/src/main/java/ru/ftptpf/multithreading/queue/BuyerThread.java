package ru.ftptpf.multithreading.queue;

import java.util.Queue;

public class BuyerThread implements Runnable {

    private final Queue<Cashbox> cashboxes;

    public BuyerThread(Queue<Cashbox> cashboxes) {
        this.cashboxes = cashboxes;
    }

    @Override
    public void run() {
        /**
         * в блоке synchronized (cashboxes) мы захватываем монитор
         */
        try {
            synchronized (cashboxes) {
                while (true) {
                    if (!cashboxes.isEmpty()) {
                        Cashbox cashbox = cashboxes.remove();
                        System.out.println(Thread.currentThread().getName() + " обслуживается в кассе " + cashbox);

                        /**
                         * после wait мы освобождаем монитор на 3 миллисекунд
                         * и чтобы продолжить должны снова его захватить
                         */
                        cashboxes.wait(5L);

                        System.out.println(Thread.currentThread().getName() + " освобождаю кассу " + cashbox);
                        cashboxes.add(cashbox);
                        /**
                         * notifyAll пробуждает все потоки которые были в состоянии wait, и после того как освободится
                         * монитор, один из других потоков сможет захватить его
                         */
                        cashboxes.notifyAll();
                        break;

                    } else {
                        System.out.println(Thread.currentThread().getName() + " ожидает свободную кассу ");
                        cashboxes.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
