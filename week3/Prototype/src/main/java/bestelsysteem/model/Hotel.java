package bestelsysteem.model;

public class Hotel {
    public String name;

    public Hotel() {}

    public Hotel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hotel{name='" + name + "'}";
    }
}
