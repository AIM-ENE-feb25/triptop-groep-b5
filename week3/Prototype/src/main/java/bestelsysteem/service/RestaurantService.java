package bestelsysteem.service;

import bestelsysteem.adapter.RestaurantApiAdapter;
import bestelsysteem.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantApiAdapter adapter;

    public RestaurantService(RestaurantApiAdapter adapter) {
        this.adapter = adapter;
    }

    public List<Restaurant> getRestaurants(String stad) {
        return adapter.getRestaurants(stad);
    }
}
