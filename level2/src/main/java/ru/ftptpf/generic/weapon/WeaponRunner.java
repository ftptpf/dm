package ru.ftptpf.generic.weapon;

import ru.ftptpf.oop.practice.Archer;
import ru.ftptpf.oop.practice.Hero;
import ru.ftptpf.oop.practice.Warrior;

public class WeaponRunner {

    public static void main(String[] args) {
        Archer<Bow> bowArcher = new Archer<>("Archer", 15);
        bowArcher.setWeapon(new Bow());

        Warrior<Sword> swordWarrior = new Warrior<>("Warrior", 10);
        swordWarrior.setWeapon(new Sword());

        printWeaponDamage(bowArcher);
    }

    public static <T extends Weapon> void printWeaponDamage(Hero<T> hero) {
        System.out.println(hero.getWeapon().getDamage());
    }
}
