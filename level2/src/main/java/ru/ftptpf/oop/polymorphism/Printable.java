package ru.ftptpf.oop.polymorphism;

import java.util.Random;

public interface Printable {

    String SOME_VALUR = "Example";
    Random RANDOM = new Random();

    default void printWithRandom() {
        System.out.println(generateRandom());
        print();
    }

    void print();

    private int generateRandom() {
        return RANDOM.nextInt();
    }
}
