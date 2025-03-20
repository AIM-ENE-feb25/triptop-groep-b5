package han.soex.prototype.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class HotelController {

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    private final RestTemplate restTemplate;

    public HotelController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hotels")
    public ResponseEntity<String> getHotels(
            @RequestParam String city,
            @RequestParam String dest_id,
            @RequestParam String locale,
            @RequestParam String checkin_date,
            @RequestParam String checkout_date,
            @RequestParam int room_number,
            @RequestParam int adults_number) {

        int destinationId;
        try {
            destinationId = Integer.parseInt(dest_id);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid dest_id format. It must be an integer.");
        }

        String url = String.format("https://booking-com.p.rapidapi.com/v1/hotels/search?city=%s&locale=%s&dest_type=city&filter_by_currency=USD&order_by=popularity&units=metric&dest_id=%d&checkin_date=%s&checkout_date=%s&room_number=%d&adults_number=%d",
                city, locale, destinationId, checkin_date, checkout_date, room_number, adults_number);


        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", "booking-com.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response;
    }

}
