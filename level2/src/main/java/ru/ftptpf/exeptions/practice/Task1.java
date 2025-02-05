package ru.ftptpf.exeptions.practice;

/**
 * Объявите переменную со значением null, вызовите у нее метод,
 * отловите возникшее исключение.
 */
public class Task1 {

    public static void main(String[] args) {
        String value = null;
/*         В реальности NullPointerException обычно никто не ловит в try catch
         программист должен следить чтобы таких ошибок не было*/
        try {
            value.length();
        } catch (NullPointerException e) {
            System.err.println("Catch");
            e.printStackTrace();
        }
    }
}
