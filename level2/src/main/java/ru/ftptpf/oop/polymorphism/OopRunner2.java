package ru.ftptpf.oop.polymorphism;

import ru.ftptpf.oop.encapsulation.Computer;
import ru.ftptpf.oop.encapsulation.Ram;
import ru.ftptpf.oop.encapsulation.Ssd;
import ru.ftptpf.oop.inheritance.Laptop;

public class OopRunner2 {

    public static void main(String[] args) {
        Computer laptop = new Laptop(new Ram(111), new Ssd(222), 2);
        Mobile mobile = new Mobile(new Ram(333), new Ssd(444));

        print(laptop, mobile);
        printWithRandom(laptop, mobile);
    }

    public static void print(Printable... objects) {
        for (Printable object : objects) {
            object.print();
        }
    }

    public static void printWithRandom(Printable... objects) {
        for (Printable object : objects) {
            object.printWithRandom();
        }
    }
}
