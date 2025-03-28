package groep.b5;

import groep.b5.entity.Person;
import groep.b5.furniture.Cooling;
import groep.b5.furniture.Heating;
import groep.b5.furniture.Lightning;
import groep.b5.furniture.MusicSystem;

public class Main {
    public static void main(String[] args) {
        Person workingPerson = new Person();
        workingPerson.returnFromWork();

        Heating heat = new Heating();
        heat.heatToTemperature(20);

        MusicSystem musicSystem = new MusicSystem();
        musicSystem.on();
        musicSystem.playMusic();

        Lightning lightning = new Lightning();
        lightning.on();
        lightning.dim();
    }
}