package groep.b5.entity;

import groep.b5.furniture.ClimateControl;
import groep.b5.furniture.Lightning;
import groep.b5.furniture.MusicSystem;

public class SmartHomeSystem {
    private final ClimateControl climateControl;
    private final MusicSystem musicSystem;
    private final Lightning lightning;
    private final Person person;

    public SmartHomeSystem() {
        this.climateControl = new ClimateControl();
        this.musicSystem = new MusicSystem();
        this.lightning = new Lightning();
        this.person = new Person();
    }

    public void scenarioReturnFromWork() {
        person.returnFromWork();
        climateControl.heatToTemperature(20);
        musicSystem.on();
        musicSystem.playMusic();
        lightning.on();
        lightning.dim();
    }

    public void scenarioGoToBed() {
        musicSystem.off();
        lightning.off();
        climateControl.coolTemperature(18);
        System.out.println("Person goes to bed");
    }
}