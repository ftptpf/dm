package ru.ftptpf.oop.practice;

public class TrainingGround {

    public static void main(String[] args) {
        Hero warrior = new Warrior("Воин", 15);
        Hero mage = new Mage("Маг", 20);
        Hero archer = new Archer("Лучник", 10);

        Enemy enemy = new Enemy("Зомби", 100);

        attackEnemy(enemy, warrior, mage, archer);
    }

    public static void attackEnemy(Enemy enemy, Hero... heroes) {
        while (enemy.isAlive()) {
            for (Hero hero : heroes) {
                if (enemy.isAlive()) {
                    hero.attackEnemy(enemy);
                }
            }
        }
    }
}
