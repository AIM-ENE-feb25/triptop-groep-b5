package codevoorbeeld.demo.domain;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name);
    }

    @Override
    public String attack() {
        return name + " attacks with a sword!";
    }
}
