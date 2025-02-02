package ru.ftptpf.oop.practice;

import ru.ftptpf.generic.weapon.MagicWeapon;

public class Mage<T extends MagicWeapon> extends Hero<T> {

    public Mage(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " сотворил заклинание причинив урон " + enemy.getName());
        enemy.takeDamage(getDamage());
    }
}
