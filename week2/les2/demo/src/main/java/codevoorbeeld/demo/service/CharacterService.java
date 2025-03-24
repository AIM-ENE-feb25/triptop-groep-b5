package codevoorbeeld.demo.service;

import codevoorbeeld.demo.domain.Character;
import codevoorbeeld.demo.domain.Mage;
import codevoorbeeld.demo.domain.Warrior;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CharacterService {
    private final List<Character> characters = List.of(
            new Mage("Fire Mage"),
            new Warrior("Knight")
    );

    public List<String> attackAll() {
        return characters.stream().map(Character::attack).toList();
    }
}
