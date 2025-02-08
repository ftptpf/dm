package ru.ftptpf.inout;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class InputStreamRunner {

    public static void main(String[] args) throws IOException {
/*        File file = new File(
                String.join(
                        File.separator, "level2", "src", "main", "java", "ru", "ftptpf", "inout", "resources", "input-stream-runner.txt")
        );*/
        Path path = Path.of("level2", "src", "main", "java", "ru", "ftptpf", "inout", "resources", "input-stream-runner.txt");
        File file = path.toFile();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
/*            byte[] bytes = fileInputStream.readAllBytes();
            String string = new String(bytes);
            System.out.println(string);*/

            byte[] bytes = new byte[fileInputStream.available()];
            int counter = 0;
            byte currentByte;
            while ((currentByte = (byte) fileInputStream.read()) != -1) {
                bytes[counter++] = currentByte;
            }
            String value = new String(bytes);
            System.out.println(value);
        }
    }
}
