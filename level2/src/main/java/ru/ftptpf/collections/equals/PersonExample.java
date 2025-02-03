package ru.ftptpf.collections.equals;

/**
 * По умолчанию и equal и hashCode оперируют областью памяти которые выделены под объект,
 * т.е. оперируют ссылкой, а не его значением.
 * Мы же чаще всего хотим оперировать значением объекта. Поэтому переопределяем эти методы.
 */
public class PersonExample {

    public static void main(String[] args) {

        Person ivan = new Person(1, "Ivan", "Ivanov");
        Person petr = new Person(2, "Petro", "Petrov");

        System.out.println(ivan.hashCode());
        System.out.println(petr.hashCode());
        System.out.println(ivan.equals(petr));

    }
}
