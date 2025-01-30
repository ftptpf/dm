package ru.ftptpf.string;

/**
 * Заменить все грустные смайлы :( в строе на веселые :)
 */
public class Task1 {
    public static void main(String[] args) {
        String str = "ewwer :( weerew :( sdgsdfsdf :) sdfdfs :)";
        System.out.println(str);
        String result = replace(str);
        System.out.println(result);
    }

    private static String replace(String str) {
        return str.replace(":(", ":)");
    }
}
