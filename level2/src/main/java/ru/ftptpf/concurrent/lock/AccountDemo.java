package ru.ftptpf.concurrent.lock;

public class AccountDemo {

    public static void main(String[] args) {
        var accountFrom = new Account(20_000);
        var accountTo = new Account(20_000);

        AccountThread accountThread1 = new AccountThread(accountFrom, accountTo);
        AccountThread accountThread2 = new AccountThread(accountTo, accountFrom);

        accountThread1.start();
        accountThread2.start();

        try {
            accountThread1.join();
            accountThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(accountFrom);
        System.out.println(accountTo);
    }
}
