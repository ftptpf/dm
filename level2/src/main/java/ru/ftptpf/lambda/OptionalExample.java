package ru.ftptpf.lambda;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalExample {

    public static void main(String[] args) {
        Optional<Student> maybeStudent = Stream.of(
                        new Student(18, "Ivan"),
                        new Student(20, "Petr"),
                        new Student(34, "Vasya"),
                        new Student(45, "Sveta"),
                        new Student(101, "Katya"),
                        new Student(69, "Denis"),
                        new Student(24, "Kira")
                )
                .filter(student -> student.getAge() < 30)
                .reduce((student1, student2) -> student1.getAge() > student2.getAge() ? student1 : student2);
        maybeStudent.ifPresent(System.out::println);

        maybeStudent.map(Student::getAge)
                .flatMap(age -> Optional.of(age * 2))
                .ifPresent(System.out::println);
    }
}
