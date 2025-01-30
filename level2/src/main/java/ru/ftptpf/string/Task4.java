package ru.ftptpf.string;

/**
 * Подсчитать количество всех точек, запятых и восклицательных знаков в строке.
 */
public class Task4 {

    public static void main(String[] args) {
        String value = "Hello,,,, World!///...";
        System.out.println(count(value));
    }

    public static int count(String str) {
        String result = str.replace(".", "")
                .replace(",", "")
                .replace("!", "");
        return str.length() - result.length();
    }
}
