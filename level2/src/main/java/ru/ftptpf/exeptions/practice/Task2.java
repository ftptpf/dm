package ru.ftptpf.exeptions.practice;

/**
 * Написать код, который создаст, а потом отловит ArrayIndexOutOfBoundsException
 */
public class Task2 {

    public static void main(String[] args) {
        int[] values = {1, 4, 6, 7, 8};
        /*         В реальности ArrayIndexOutOfBoundsException обычно никто не ловит в try catch
         программист должен следить чтобы таких ошибок не было*/
        try {
            System.out.println(values[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Catch");
            e.printStackTrace();
        }
    }
}
