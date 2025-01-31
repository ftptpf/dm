package ru.ftptpf.oop.encapsulation;

import ru.ftptpf.oop.inheritance.Laptop;

public class OopRunner {

    public static void main(String[] args) {
        Ssd ssd = new Ssd(1024);
        Ram ram = new Ram(2048);
        Computer computer = new Computer(ram, ssd);
        computer.load();

        Laptop laptop = new Laptop(new Ram(512), new Ssd(250), 2);
        laptop.open();
        laptop.load();
    }
}
