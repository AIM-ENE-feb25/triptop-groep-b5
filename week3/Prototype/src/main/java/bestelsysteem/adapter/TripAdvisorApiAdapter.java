package bestelsysteem.adapter;

import bestelsysteem.external.TripadvisorDriver;
import bestelsysteem.model.Hotel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TripAdvisorApiAdapter implements HotelAdapter {
    @Autowired
    TripadvisorDriver tripadvisorDriver;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getLocation(String location) {
        try {
            JsonNode rootNode = objectMapper.readTree(tripadvisorDriver.getLocation(location));
            JsonNode dataNode = rootNode.path("data").path(0).path("geoId");

            return String.valueOf(dataNode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map response to Hotel", e);
        }
    }

    @Override
    public List<Hotel> getHotels(String locationCode) {
        List<Hotel> hotels = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(tripadvisorDriver.getHotels(locationCode));
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
