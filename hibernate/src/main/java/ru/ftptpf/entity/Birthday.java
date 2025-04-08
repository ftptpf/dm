package ru.ftptpf.entity;

import java.time.LocalDate;

public record Birthday(LocalDate birthDate) {

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}
