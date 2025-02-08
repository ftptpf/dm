package ru.ftptpf.inout;

import java.io.File;
import java.io.IOException;

/**
 * Output Stream (поток байтов из нашего приложения в какое то хранилище)
 * application -------> file
 * <p>
 * Input Stream (поток байтов из какого то источника в наше приложение)
 * application <------- file
 */
public class FileRunner {

    public static void main(String[] args) throws IOException {
        File file = new File("level2/src/main/java/ru/ftptpf/inout/resources/file-runner.txt");
        System.out.println(file.createNewFile());
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.getName());
        System.out.println(file.getParent());
    }
}
