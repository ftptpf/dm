package ru.ftptpf.reflection.model;

public abstract class Person {

    private final Long id;

    public Person(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{"
                + "id=" + id
                + '}';
    }
}