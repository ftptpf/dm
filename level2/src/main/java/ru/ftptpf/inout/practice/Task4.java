package ru.ftptpf.inout.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Задан файл с Java кодом. Прочитать текст программы из файла и все слова
 * public заменить на private. Результат сохранить в другой заранее созданный файл.
 */
public class Task4 {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("level2/src/main/java/ru/ftptpf/inout/resources/task4-java-file.txt");
        String stringValue = Files.readString(path);
        String stringResult = stringValue.replace("public", "private");
        Path pathResult = Path.of("level2/src/main/java/ru/ftptpf/inout/resources/task4-java-file-result.txt");
        Files.writeString(pathResult, stringResult);
    }
}
