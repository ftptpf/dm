package ru.ftptpf.inout.practice;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Задан файл с Java кодом. Прочитать текст программы из файли и записать в другой файл
 * в обратном порядке символы каждой строки.
 */
public class Task5 {

    public static void main(String[] args) {
        Path path = Path.of("level2/src/main/java/ru/ftptpf/inout/resources/task4-java-file.txt");
        Path pathResult = Path.of("level2/src/main/java/ru/ftptpf/inout/resources/task5-java-file-revers.txt");
        try (Stream<String> stringStream = Files.lines(path);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(pathResult, APPEND, CREATE)) {
            stringStream.map(StringBuilder::new)
                    .map(StringBuilder::reverse)
                    .forEach(line -> {
                        try {
                            bufferedWriter.append(line.toString());
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
