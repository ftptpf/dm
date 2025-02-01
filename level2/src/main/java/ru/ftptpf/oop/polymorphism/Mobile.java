package ru.ftptpf.oop.polymorphism;

import ru.ftptpf.oop.encapsulation.Computer;
import ru.ftptpf.oop.encapsulation.Ram;
import ru.ftptpf.oop.encapsulation.Ssd;

public class Mobile extends Computer {

    public Mobile(Ram ram, Ssd ssd) {
        super(ram, ssd);
    }
}
