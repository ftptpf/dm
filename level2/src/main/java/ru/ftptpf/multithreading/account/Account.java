package ru.ftptpf.multithreading.account;

public class Account {

    private static int generatorId = 1;
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

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ", money=" + money
                + '}';
    }
}
