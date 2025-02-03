package ru.ftptpf.collections.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {

    public static void main(String[] args) {
        List<Integer> sourseList = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> list = new ArrayList<>(sourseList);

/*        for (Integer integer : list) {
            System.out.println(integer);
        }*/

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 3 || next == 4) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}