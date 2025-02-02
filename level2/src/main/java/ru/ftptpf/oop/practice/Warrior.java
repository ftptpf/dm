package ru.ftptpf.oop.practice;

import ru.ftptpf.generic.weapon.MeleeWeapon;

public class Warrior<T extends MeleeWeapon> extends Hero<T> {

    public Warrior(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " взмахнул мечем и ударил " + enemy.getName());
        enemy.takeDamage(getDamage());
    }
}
