package ru.ftptpf.string;

/**
 * Написать функцию, принимающую в качестве параметров:
 * фамилию, имя, отчество и возвращающую инициалы в формате "Ф.И.О"
 * Учесть что входные параметры могут быть в любом регистре,
 * а результирующая строка должны быть в верхнем регистре.
 */
public class Task3 {

    public static void main(String[] args) {
        String firstName = "Иван";
        String middleName = "Сергеевич";
        String lastName = "Лавров";
        System.out.println(format(firstName, middleName, lastName));
    }

    public static String format(String firstName, String middleName, String lastName) {
        char first = Character.toUpperCase(firstName.charAt(0));
        char middle = Character.toUpperCase(middleName.charAt(0));
        char last = Character.toUpperCase(lastName.charAt(0));
        return String.format("%s.%s.%s", last, first, middle);
    }
}
