package ru.ftptpf.introduction.composition;

public class CompositionRunner {

    public static void main(String[] args) {
        Ram ram = new Ram(1000);
        Ssd ssd = new Ssd(5000);
        Computer computer = new Computer(ram, ssd);
        computer.printState();
    }

}
