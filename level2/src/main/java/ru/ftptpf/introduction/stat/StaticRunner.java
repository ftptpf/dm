package ru.ftptpf.introduction.stat;


import ru.ftptpf.introduction.composition.Computer;

public class StaticRunner {

    public static void main(String[] args) {
        new Computer(null, null);
        new Computer(null, null);
        new Computer(null, null);
        new Computer(null, null);
        Computer computer = new Computer(null, null);
        System.out.println(computer.getClass());

        System.out.println(Computer.getCounter());

    }
}
