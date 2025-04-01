package bestelsysteem.adapter;

import bestelsysteem.model.Hotel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class BookingApiAdapter implements HotelAdapter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getLocation(String location) {
        String encodedLocation = null;
        try {
            encodedLocation = URLEncoder.encode(location, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchDestination?query="+encodedLocation))
                .header("x-rapidapi-key", "--")
                .header("x-rapidapi-host", "booking-com15.p.rapidapi.com")
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
            JsonNode dataNode = rootNode.path("data").path(0).path("dest_id");

            return dataNode.asText();

        } catch (Exception e) {
            throw new RuntimeException("Failed to map response to Hotel", e);
        }
    }

    @Override
    public List<Hotel> getHotels(String locationCode) {
        List<Hotel> hotels = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotels?dest_id="+ locationCode +"&search_type=CITY&arrival_date=2025-05-01&departure_date=2025-05-10"))
                .header("x-rapidapi-key", "--")
                .header("x-rapidapi-host", "booking-com15.p.rapidapi.com")
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
            JsonNode dataNode = rootNode.path("data").path("hotels");
            for(JsonNode node : dataNode) {
                hotels.add(new Hotel(node.path("property").get("name").asText()));
                //System.out.println(node.path("property").get("name"));
            }
            //System.out.println(hotels);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map response to Hotel", e);
        }

        //System.out.println(hotels.size());
        return hotels;
    }

    @Override
    public String getApiName() {
        return "Booking";
    }
}
