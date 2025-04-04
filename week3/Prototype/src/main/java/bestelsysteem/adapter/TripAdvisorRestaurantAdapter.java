package bestelsysteem.adapter;

import bestelsysteem.model.Restaurant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class TripAdvisorRestaurantAdapter implements RestaurantApiAdapter {

    @Value("${restaurant.api.key}")
    private String apiKey;

    @Value("${restaurant.api.baseurl}")
    private String baseUrl;

    private static final String API_HOST = "travel-advisor.p.rapidapi.com";

    private static final Map<String, String[]> CITY_COORDINATES = Map.of(
            "arnhem", new String[]{"51.9851", "5.8987"},
            "utrecht", new String[]{"52.0907", "5.1214"},
            "amsterdam", new String[]{"52.3676", "4.9041"},
            "rotterdam", new String[]{"51.924419", "4.477733"}
    );

    @Override
    public List<Restaurant> getRestaurants(String city) {
        try {
            String[] coords = CITY_COORDINATES.get(city.toLowerCase());
            if (coords == null) {
                System.out.println("Geen coördinaten gevonden voor stad: " + city);
                return Collections.emptyList();
            }

            String lat = coords[0];
            String lng = coords[1];

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "?latitude=" + lat + "&longitude=" + lng + "&limit=5"))
                    .header("X-RapidAPI-Key", apiKey)
                    .header("X-RapidAPI-Host", API_HOST)
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            List<Restaurant> result = new ArrayList<>();
            for (JsonNode node : root.get("data")) {
                if (node.has("name") && node.has("rating")) {
                    String name = node.get("name").asText();
                    String address = node.has("address") ? node.get("address").asText("") : "";
                    double rating = node.get("rating").asDouble(0.0);
                    result.add(new Restaurant(name, address, rating));
                }
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
