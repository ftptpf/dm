package ru.ftptpf.exeptions;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeoutException;

public class ExceptionExample {

    public static void main(String[] args) {
        System.out.println("main start");
        try {
            unsafe(-10);
        } catch (FileNotFoundException | TimeoutException exception) {
            // здесь мы можем как то обработать наше исключение
            // использовать какую то систему логирования
            // либо вывести на консоль весь стек трейс ошибки
            exception.printStackTrace();
        } finally {
            System.out.println("finally");
            // здесь мы можем выполнить код, который будет выполняться в любом случае
            // есть исключение или нет
            // например здесь можно указать закрытие ресурсов
        }
        // после того как мы отловили исключение, мы дальше можем выполнять наш код
        System.out.println("main end");

    }

    public static void unsafe(int value) throws FileNotFoundException, TimeoutException {
        System.out.println("unsafe start");
        if (value > 0) {
            throw new FileNotFoundException("File not found");
        }
        // после исключение здесь уже код не будет выполнять, так как исключение не обработано,
        // а проброшено в выше
        System.out.println("unsafe end");
    }
}
