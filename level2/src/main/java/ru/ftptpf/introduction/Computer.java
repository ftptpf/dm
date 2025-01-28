package ru.ftptpf.introduction;

public class Computer {

    private int ssd = 500;
    private int ram = 1024;

    public Computer() {
        System.out.println("Я был создан");
    }

    Computer(int ssd, int ram) {
        this.ssd = ssd;
        this.ram = ram;
    }

    void load() {
        System.out.println("Я загрузился");
    }

    void printState() {
        System.out.println("SSD: " + ssd + ", RAM: " + ram);
    }
}
