package codevoorbeeld.demo.controller;

import codevoorbeeld.demo.service.CharacterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private final CharacterService characterService;

    public GameController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @RequestMapping("/attack")
    public List<String> attackAll() {
        return characterService.attackAll();
    }
}
