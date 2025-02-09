package ru.ftptpf.inout.practice;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Задан файл с текстом, найти и вывести на консоль все слова,
 * для которых последняя буква одного слова совпадает с первой буквой следующего слова.
 */
public class Task2 {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("level2/src/main/java/ru/ftptpf/inout/resources/input-stream-runner.txt");
        try (Scanner scanner = new Scanner(path)) {
            String prev = null;
            if (scanner.hasNext()) {
                prev = scanner.next();
            }
            while (scanner.hasNext()) {
                String current = scanner.next();
                if (isEqualLastSymbolAndFirst(prev, current)) {
                    System.out.println(prev + " " + current);
                }
                prev = current;
            }
        }
    }

    private static boolean isEqualLastSymbolAndFirst(String prev, String current) {
        return prev.charAt(prev.length() - 1) == current.charAt(0);
    }
}
