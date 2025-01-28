package ru.ftptpf.introduction.composition;

public class Computer {

    private Ram ram;
    private Ssd ssd;

    public Computer(Ram ram, Ssd ssd) {
        this.ram = ram;
        this.ssd = ssd;
    }

    public void printState() {
        System.out.println("Computer state: Ram " + ram.getSize() + ", SSD " + ssd.getSize());
    }
}
