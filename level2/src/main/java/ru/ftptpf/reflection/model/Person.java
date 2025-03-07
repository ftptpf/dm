package ru.ftptpf.reflection.model;

public abstract class Person {

    private long id;

    public Person(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}