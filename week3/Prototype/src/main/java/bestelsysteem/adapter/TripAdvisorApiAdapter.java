package bestelsysteem.adapter;

import bestelsysteem.model.Hotel;
import bestelsysteem.model.HotelResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class TripAdvisorApiAdapter implements HotelAdapter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Hotel> getHotels() {
        List<Hotel> hotels = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tripadvisor16.p.rapidapi.com/api/v1/hotels/searchHotels?geoId=188590&checkIn=2025-04-01&checkOut=2025-04-06"))
                .header("x-rapidapi-key", "--")
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
        //System.out.println(response.body());
        try {
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode dataNode = rootNode.path("data").path("data");
            for(JsonNode node : dataNode) {
                hotels.add(new Hotel(node.get("title").asText()));
                System.out.println(node.get("title"));
            }
            System.out.println(hotels);
            //String name = dataNode.has("title") ? rootNode.get("title").asText() : null;
            //return new Hotel(name);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map response to Hotel", e);
        }


        return null;
    }

    @Override
    public String getApiName() {
        return "TripAdvisor";
    }
}
