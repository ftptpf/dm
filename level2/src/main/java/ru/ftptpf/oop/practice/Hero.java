package ru.ftptpf.oop.practice;

import ru.ftptpf.generic.weapon.Weapon;

public abstract class Hero<T extends Weapon> {

    private final String name;
    private int damage;
    private T weapon;

    public Hero(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public abstract void attackEnemy(Enemy enemy);

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public T getWeapon() {
        return weapon;
    }

    public void setWeapon(T weapon) {
        this.weapon = weapon;
    }
}
