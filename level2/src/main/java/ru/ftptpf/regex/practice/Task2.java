package ru.ftptpf.regex.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Написать программу, выполняющую поиск в стоке шестнадцатиричных чисел, записанных по правилам Java,
 * с помощью регулярных выражений и вывести их на страницу.
 */
public class Task2 {

    public static void main(String[] args) {
        String regex = "0[xX][0-9a-fA-F]+";
        String input = "0x123 ewwrwe 0X123 jhjyt 0x1234567890ABCDEFabcdef 1x123 wqewqe 0t123";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
