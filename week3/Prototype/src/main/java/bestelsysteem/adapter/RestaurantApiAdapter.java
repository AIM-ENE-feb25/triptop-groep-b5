package bestelsysteem.adapter;

import bestelsysteem.model.Restaurant;

import java.util.List;

public interface RestaurantApiAdapter {
    List<Restaurant> getRestaurants(String city);
}
