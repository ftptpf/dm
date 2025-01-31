package ru.ftptpf.oop.inheritance;

import ru.ftptpf.oop.encapsulation.Computer;
import ru.ftptpf.oop.encapsulation.Ram;
import ru.ftptpf.oop.encapsulation.Ssd;

/**
 * Класс Laptop наследуется от класса Computer, который наследуется от класса Object
 */
public class Laptop extends Computer {

    private int weight;

    {
        System.out.println("Блок инициализации класса Laptop");
    }

    static {
        System.out.println("Блок статической инициализации класса Laptop");
    }

    public Laptop(Ram ram, Ssd ssd, int weight) {
        super(ram, ssd);
        this.weight = weight;
    }

    public void open() {
        System.out.println("Открыл крышку");
    }

    public int getWeight() {
        return weight;
    }
}
