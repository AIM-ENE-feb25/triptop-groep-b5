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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/hotels")  // Correct URL path as per your requirement
public class HotelController {

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    private final RestTemplate restTemplate;

    public HotelController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/search")  // Ensure this matches the search URL path
    public ResponseEntity<String> searchHotels(
            @RequestParam String dest_id,          // Required
            @RequestParam String search_type,     // Required
            @RequestParam String arrival_date,    // Required
            @RequestParam String departure_date,  // Required
            @RequestParam String checkin_date,    // Add this required field
            @RequestParam String checkout_date,   // Add this required field
            @RequestParam(required = false) List<Integer> children_age,
            @RequestParam(required = false) Integer adults,
            @RequestParam(required = false) Integer room_qty,
            @RequestParam(required = false) Integer page_number,
            @RequestParam(required = false) String units,
            @RequestParam(required = false) String temperature_unit,
            @RequestParam(required = false) String languagecode,
            @RequestParam(required = false) String currency_code,
            @RequestParam(required = false) String locale,
            @RequestParam(required = false) String dest_type,
            @RequestParam(required = false) String filter_by_currency,
            @RequestParam(required = false) String order_by,
            @RequestParam(required = false) Integer room_number,
            @RequestParam(required = false) Integer adults_number) {

        String url = "https://booking-com.p.rapidapi.com/v1/hotels/search";

        // Convert children_age list to a comma-separated string if present
        String childrenAges = (children_age != null) ?
                children_age.stream().map(String::valueOf).collect(Collectors.joining(",")) : "";

        // Set default values for optional parameters if they are null
        adults = (adults != null) ? adults : 1;
        room_qty = (room_qty != null) ? room_qty : 1;
        page_number = (page_number != null) ? page_number : 1;
        units = (units != null) ? units : "metric";
        temperature_unit = (temperature_unit != null) ? temperature_unit : "c";
        languagecode = (languagecode != null) ? languagecode : "en-us";
        currency_code = (currency_code != null) ? currency_code : "EUR";
        locale = (locale != null) ? locale : "en";
        dest_type = (dest_type != null) ? dest_type : "CITY";
        filter_by_currency = (filter_by_currency != null) ? filter_by_currency : "false";
        order_by = (order_by != null) ? order_by : "popularity";
        room_number = (room_number != null) ? room_number : 1;
        adults_number = (adults_number != null) ? adults_number : 1;

        // Build query parameters for the API request
        String params = String.format(
                "?dest_id=%s&search_type=%s&arrival_date=%s&departure_date=%s&checkin_date=%s&checkout_date=%s&children_age=%s&adults=%d&room_qty=%d&page_number=%d&units=%s&temperature_unit=%s&languagecode=%s&currency_code=%s&locale=%s&dest_type=%s&filter_by_currency=%s&order_by=%s&room_number=%d&adults_number=%d",
                dest_id, search_type, arrival_date, departure_date, checkin_date, checkout_date, childrenAges, adults, room_qty, page_number, units,
                temperature_unit, languagecode, currency_code, locale, dest_type, filter_by_currency, order_by, room_number, adults_number);

        // Set the headers for the request, including the RapidAPI key
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", "booking-com.p.rapidapi.com");

        // Create an HttpEntity to send with the request
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the API request and return the response
        ResponseEntity<String> response = restTemplate.exchange(url + params, HttpMethod.GET, entity, String.class);

        return response;
    }
}
