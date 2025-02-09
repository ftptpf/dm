package ru.ftptpf.inout;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesRunner {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("level2", "src", "main", "java", "ru", "ftptpf", "inout", "resources", "files-runner.txt");
        try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
            fileWriter.append("Hello world");
            fileWriter.append(" Java!");
            fileWriter.newLine();
        }
    }
}
