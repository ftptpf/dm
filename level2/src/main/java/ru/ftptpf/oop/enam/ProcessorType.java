package ru.ftptpf.oop.enam;

public enum ProcessorType {
    BIT_32("bit-32"),
    BIT_64("bit-64");

    private String name;

    ProcessorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
