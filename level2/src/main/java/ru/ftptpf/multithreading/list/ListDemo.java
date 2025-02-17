package ru.ftptpf.multithreading.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        var thread1 = new ListThread(list);
        var thread2 = new ListThread(list);
        var thread3 = new ListThread(list);
        var thread4 = new ListThread(list);
        var thread5 = new ListThread(list);
        var thread6 = new ListThread(list);
        var thread7 = new ListThread(list);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }
}
