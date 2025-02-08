package ru.ftptpf.inout;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class OutputRunner {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("level2", "src", "main", "java", "ru", "ftptpf", "inout", "resources", "output-stream-runner.txt");
        File file = path.toFile();
        try (BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(file, true))) {
            String value = "Hello world!";
            fileOutputStream.write(value.getBytes());
            fileOutputStream.write(System.lineSeparator().getBytes());
        }
    }
}
