package ru.ftptpf.introduction;

public class ComputerRunner {

    public static void main(String[] args) {
        Computer computer1 = new Computer();
        computer1.load();
        computer1.printState();

        Computer computer2 = new Computer(1000, 2048);
        computer2.printState();

    }
}
