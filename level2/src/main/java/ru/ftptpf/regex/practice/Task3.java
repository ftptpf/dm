package ru.ftptpf.regex.practice;

/**
 * Написать программу, выполняющую поиск в строке всех тегов абзацев, в т.ч. тех,
 * у которых есть параметры, например <p id="p1">, и заменить их на простой тег <p>.
 */
public class Task3 {

    public static void main(String[] args) {
        String regex = "(<p .+?>)(.+?</p>)";
        String input = "<p> www dfsdfsdf </p> <b> dsfsdf <p id=\"p1\"> 11111 </p> </b> sdsa <p> sdsd";
        System.out.println(input.replaceAll(regex, "<p>$2"));
    }
}
