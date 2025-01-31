package ru.ftptpf.oop.encapsulation;

public class Computer {

    private Ram ram;
    private Ssd ssd;

    {
        System.out.println("Блок инициализации класса Computer");
    }

    static {
        System.out.println("Блок статической инициализации класса Computer");
    }

    public Computer(Ram ram, Ssd ssd) {
        this.ram = ram;
        this.ssd = ssd;
    }

    public void load() {
        System.out.println("Я загрузился");
    }
}
