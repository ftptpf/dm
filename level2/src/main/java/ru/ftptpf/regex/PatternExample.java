package ru.ftptpf.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternExample {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d{3}");
        Matcher matcher = pattern.matcher("123");
        System.out.println(matcher.matches());

        String phoneNumber = "+7(905)876-54-32";
        Pattern patternPhone = Pattern.compile("\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}");
        Matcher phoneMatcher = patternPhone.matcher(phoneNumber);
        System.out.println(phoneMatcher.matches());

        System.out.println(Pattern.matches(patternPhone.toString(), phoneNumber));
        System.out.println(phoneNumber.matches(patternPhone.toString()));
    }
}
