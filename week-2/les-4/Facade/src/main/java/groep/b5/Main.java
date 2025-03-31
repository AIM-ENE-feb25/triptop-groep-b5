package groep.b5;

import groep.b5.entity.SmartHomeSystem;

public class Main {
    public static void main(String[] args) {
        SmartHomeSystem smartHomeSystem = new SmartHomeSystem();

        smartHomeSystem.scenarioReturnFromWork();

        smartHomeSystem.scenarioGoToBed();
    }
}