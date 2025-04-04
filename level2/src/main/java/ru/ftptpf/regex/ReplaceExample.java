package ru.ftptpf.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceExample {

    public static void main(String[] args) {
        String phoneNumber = " sdfwqwe +7(916)111-55-33 sdsad sa +7(905)876-54-32 wewewe";
        String regex = "\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuilder, "XXX");
        }
        matcher.appendTail(stringBuilder);
        System.out.println(stringBuilder);

        System.out.println(phoneNumber.replaceAll(regex, "ZZZ"));
    }
}