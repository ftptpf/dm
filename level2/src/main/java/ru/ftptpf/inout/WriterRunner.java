package ru.ftptpf.inout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class WriterRunner {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("level2", "src", "main", "java", "ru", "ftptpf", "inout", "resources", "writer-runner.txt");
        File file = path.toFile();
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true))) {
            fileWriter.append("Hello world");
            fileWriter.append(" Java!");
            fileWriter.newLine();
        }
    }
}
