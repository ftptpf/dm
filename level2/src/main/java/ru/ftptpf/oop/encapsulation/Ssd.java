package ru.ftptpf.oop.encapsulation;

public class Ssd {

    /**
     * Приветные поля - это инкапсуляция
     */
    private int size;

    public Ssd(int size) {
        this.size = size;
    }

    /**
     * Открытая часть класса - это "интерфейс" которым могут пользоваться другие пользователи
     *
     * @return
     */
    public int getSize() {
        return size;
    }
}
