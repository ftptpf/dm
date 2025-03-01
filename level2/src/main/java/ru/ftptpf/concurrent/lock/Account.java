package ru.ftptpf.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private static int generatorId = 1;
    private final Lock lock = new ReentrantLock();
    private int id;
    private int money;

    public Account(int money) {
        this.money = money;
        this.id = generatorId++;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public boolean takeOff(int money) {
        if (this.money >= money) {
            this.money -= money;
            return true;
        }
        return false;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ", money=" + money
                + '}';
    }
}
