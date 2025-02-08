package ru.ftptpf.inout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class ReaderRunner {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("level2", "src", "main", "java", "ru", "ftptpf", "inout", "resources", "input-stream-runner.txt");
        File file = path.toFile();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String collect = fileReader.lines()
                    .collect(Collectors.joining("\n"));
            System.out.println(collect);
        }
    }
}
