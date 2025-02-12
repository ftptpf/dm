package ru.ftptpf.serialization;

import java.io.Serializable;

/**
 * Чтобы не было проблем с десериалазицией всегда в Serializable классах нужно указывать serialVersionUID.
 * Иначе он будет создан по умолчания сам и если в класс вносились изменения после сериализации,
 * при десереализации может быть ошибка.
 * При сериализации - десериализации статические поля не записываются и не считываются из потока
 * (за исключением serialVersionUID).
 * Так как статические поля относятся к классу, а не объекту.
 * Если мы не хотим чтобы какое-то поле сериализовалось мы помечаем его как transient,
 * например private transient String firstName;
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private int age;
    private transient String firstName;

    public Person(int age, String firstName) {
        this.age = age;
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Person{"
                + "age=" + age
                + ", firstName='" + firstName + '\''
                + '}';
    }
}
