package bestelsysteem.adapter;

import bestelsysteem.model.Hotel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class TripAdvisorApiAdapter implements HotelAdapter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${tripadvisor.api.key}")
    private String apiKey;

    @Override
    public String getLocation(String location) {
        String encodedLocation = null;
        try {
            encodedLocation = URLEncoder.encode(location, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tripadvisor16.p.rapidapi.com/api/v1/hotels/searchLocation?query=" + encodedLocation))
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", "tripadvisor16.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode dataNode = rootNode.path("data").path(0).path("geoId");

            return String.valueOf(dataNode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map response to Hotel", e);
        }

    }

    @Override
    public List<Hotel> getHotels(String locationCode) {
        List<Hotel> hotels = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tripadvisor16.p.rapidapi.com/api/v1/hotels/searchHotels?geoId="+ locationCode +"&checkIn=2025-05-01&checkOut=2025-05-10"))
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", "tripadvisor16.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode dataNode = rootNode.path("data").path("data");
            for(JsonNode node : dataNode) {
                hotels.add(new Hotel(node.get("title").asText()));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to map response to Hotel", e);
        }


        return hotels;
    }

    @Override
    public String getApiName() {
        return "TripAdvisor";
    }
}
