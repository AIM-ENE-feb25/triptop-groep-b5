package codevoorbeeld.demo.domain;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name);
    }

    @Override
    public String attack() {
        return "Warrior " + name + " attacks with a sword!";
    }
}
