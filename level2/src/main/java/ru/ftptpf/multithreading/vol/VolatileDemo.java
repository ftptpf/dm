package ru.ftptpf.multithreading.vol;

public class VolatileDemo {

    /**
     * volatile говорит что мы не должны использовать никаких оптимизаций
     * (в том числе не использовать кэш процессора)
     * volatile работает только для примитивных типов и ссылок
     */
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (!flag) {
                System.out.println("Still false ...");
            }
        });
        thread1.start();

        try {
            Thread.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(() -> {
            flag = true;
            System.out.println("Flag is set!");
        });
        thread2.start();
    }
}
