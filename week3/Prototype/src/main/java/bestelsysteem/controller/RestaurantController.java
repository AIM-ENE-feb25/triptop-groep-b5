package bestelsysteem.controller;

import bestelsysteem.model.Restaurant;
import bestelsysteem.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{city}")
    public List<Restaurant> getRestaurantsByCity(@PathVariable("city") String city) {
        return service.getRestaurants(city);
    }
}
