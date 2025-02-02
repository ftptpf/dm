package ru.ftptpf.generic;

public class List {

    private Object[] objects;
    private int size;

    public List(int initialSize) {
        this.objects = new Object[initialSize];
    }

    public void add(Object object) {
        objects[size] = object;
        size++;
    }

    public Object get(int index) {
        return objects[index];
    }

    public int getSize() {
        return size;
    }
}
