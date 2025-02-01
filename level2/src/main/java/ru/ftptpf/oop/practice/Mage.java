package ru.ftptpf.oop.practice;

public class Mage extends Hero {

    public Mage(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void attackEnemy(Enemy enemy) {
        System.out.println(getName() + " сотворил заклинание причинив урон " + enemy.getName());
        enemy.takeDamage(getDamage());
    }
}
