package codevoorbeeld.demo.domain;

public abstract class Character {
    protected String name;

    public Character(String name) {
        this.name = name;
    }

    public abstract String attack();
}
