package ru.ftptpf.introduction;

public class Computer {

    int ssd = 500;
    int ram = 1024;

    Computer() {
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
