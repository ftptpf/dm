package ru.ftptpf.exeptions.practice;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Random;

/**
 * Создать метод случайны образом выбрасывающий одно из 3-х видов исключений.
 * Вызвать этт метод в блоке try-catch. Отлавливаем одно из трех исключений.
 */
public class Task6 {

    private static final Map<Integer, Throwable> EXCEPTIONS = Map.of(
            0, new RuntimeException("runtime"),
            1, new FileNotFoundException("file not found"),
            2, new IndexOutOfBoundsException("index out of bounds")
    );

    public static void main(String[] args) {
        Random random = new Random();
        try {
            unsafe(random.nextInt(3));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void unsafe(int randomValue) throws Throwable {
        Throwable exception = EXCEPTIONS.getOrDefault(randomValue, new Task4Exception("not found"));
        throw exception;
    }
}
