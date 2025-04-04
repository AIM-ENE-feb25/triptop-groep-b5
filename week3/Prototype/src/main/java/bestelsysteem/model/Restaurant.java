package bestelsysteem.model;

public class Restaurant {
    private String name;
    private String address;
    private double rating;

    public Restaurant(String name, String address, double rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }
}
