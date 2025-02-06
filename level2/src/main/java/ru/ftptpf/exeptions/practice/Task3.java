package ru.ftptpf.exeptions.practice;

/**
 * Создать собственный класс исключение наследник Exception.
 * Создать метод выбрасывающий это исключение.
 * Вызвать этот метод и отловить исключение. Вывести stack trace в консоль.
 */
public class Task3 {

    public static void main(String[] args) {
        try {
            unsafe();
        } catch (Task3Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void unsafe() throws Task3Exception {
        throw new Task3Exception();
    }
}
