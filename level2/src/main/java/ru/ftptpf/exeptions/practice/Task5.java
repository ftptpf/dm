package ru.ftptpf.exeptions.practice;

/**
 * Бросить одно из существующих в JDK исключений, отловить его и выбросить свое сообщение,
 * указав в качестве причины отловленное.
 */
public class Task5 {

    public static void main(String[] args) {
        try {
            unsafe();
        } catch (RuntimeException exception) {
            throw new Task4Exception(exception);
        }
    }

    public static void unsafe() {
        throw new RuntimeException("Runtime ошибка");
    }
}
