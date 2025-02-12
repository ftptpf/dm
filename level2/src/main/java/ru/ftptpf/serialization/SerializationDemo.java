package ru.ftptpf.serialization;

import java.io.*;
import java.nio.file.Path;

public class SerializationDemo {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("level2", "src", "main", "java", "ru", "ftptpf", "serialization", "resources", "student.out");
        writeObject(path);
        readObject(path);
    }

    private static void writeObject(Path path) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(path.toFile()))) {
            Person ivan = new Person(25, "Ivan");
            objectOutputStream.writeObject(ivan);
        }
    }

    private static void readObject(Path path) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(path.toFile()))) {
            Person person = (Person) objectInputStream.readObject();
            System.out.println(person);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
