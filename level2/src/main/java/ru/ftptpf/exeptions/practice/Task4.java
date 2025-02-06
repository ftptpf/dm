package ru.ftptpf.exeptions.practice;

/**
 * Создать собственный класс исключение наследник RuntimeException.
 * Создать метод выбрасывающий это исключение.
 * Вызвать этот метод и отловить исключение. Вывести stack trace в консоль.
 * Добавить в конструктор своего класса возможность указать сообщение.
 */
public class Task4 {

    public static void main(String[] args) {
        try {
            unsafe();
        } catch (Task4Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void unsafe() {
        throw new Task4Exception(new RuntimeException("Some message"));
    }
}
