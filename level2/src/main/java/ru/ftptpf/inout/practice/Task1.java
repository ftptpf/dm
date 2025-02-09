package ru.ftptpf.inout.practice;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Задан файл с текстом. Вывести на консоль все слова начинающиеся с гласной буквы.
 */
public class Task1 {

    private static final String VOWELS = "уеыаоэяию";

    public static void main(String[] args) throws IOException {
        Path path = Path.of("level2/src/main/java/ru/ftptpf/inout/resources/input-stream-runner.txt");
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (VOWELS.contains(Character.toString(word.charAt(0)))) {
                    System.out.println(word);
                }
            }
        }
    }
}
