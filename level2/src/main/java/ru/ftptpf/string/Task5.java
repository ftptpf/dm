package ru.ftptpf.string;

import java.util.Arrays;

/**
 * Написать функцию, разбивающую строку на равные части по N символов и сохраняющую отдельные части в массив.
 * Вывести этот массив.
 */
public class Task5 {

    public static void main(String[] args) {
        String value = "1234567890";
        int n = 3;
        String[] results = split(value, n);
/*        for (String result : results) {
            System.out.println(result);
        }*/
        System.out.println(Arrays.toString(results));
    }

    public static String[] split(String str, int n) {
        int arraySize = (int) Math.ceil(str.length() / (double) n);
        String[] results = new String[arraySize];
        int counter = 0;
        for (int i = 0; i < str.length(); i += n) {
            int endIndex = Math.min(str.length(), i + n);
            String substring = str.substring(i, endIndex);
            results[counter] = substring;
            counter++;
        }
        return results;
    }
}
