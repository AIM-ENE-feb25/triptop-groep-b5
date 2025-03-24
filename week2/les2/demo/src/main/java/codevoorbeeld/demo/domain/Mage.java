package codevoorbeeld.demo.domain;

public class Mage extends Character {
    public Mage(String name) {
        super(name);
    }

    @Override
    public String attack() {
        return name + " attacks with a fireball!";
    }
}
