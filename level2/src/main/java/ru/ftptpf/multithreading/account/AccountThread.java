package ru.ftptpf.multithreading.account;

public class AccountThread extends Thread {

    private final Account accountFrom;
    private final Account accountTo;

    public AccountThread(Account accountFrom, Account accountTo) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    /**
     * В этом цикле происходит deadlock так как два потока захватывают ресурсы которые нужны каждому из них,
     * чтобы продолжить работу
     */
    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            synchronized (accountFrom) {
                synchronized (accountTo) {
                    if (accountFrom.takeOff(10)) {
                        accountTo.addMoney(10);
                    }
                }
            }
        }
    }
}
