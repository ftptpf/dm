package ru.ftptpf.generic;

/**
 * Класс List
 * T - тип который мы задаем при создании объекта
 *
 * @param <T>
 */
public class List<T> {

    private T[] objects;
    private int size;

    public List(int initialSize) {
        this.objects = (T[]) new Object[initialSize];
    }

    public void add(T element) {
        objects[size] = element;
        size++;
    }

    public T get(int index) {
        return objects[index];
    }

    public int getSize() {
        return size;
    }
}
