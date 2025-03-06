package ru.ftptpf.regex.practice;

import java.util.regex.Pattern;

/**
 * Написать программу, проверяющую является ли введенная строка адресом email.
 * В названии почтового ящика разрешеть использовать латинские буквы, цифры и знак подчеркивания,
 * при этом оно должно начинаться с буквы.
 * Возможные домены почтовых ящиков: org, com.
 */
public class Task1 {

    public static void main(String[] args) {
        String regex = "^[a-zA-Z]\\w*@\\w{3,}\\.(com|org)$";
        System.out.println(Pattern.matches(regex, "dm@mail.com"));
        System.out.println(Pattern.matches(regex, "1m@mail.org"));
        System.out.println(Pattern.matches(regex, "d__m@mail.com"));
        System.out.println(Pattern.matches(regex, "d__m@mail.ru"));
    }
}
