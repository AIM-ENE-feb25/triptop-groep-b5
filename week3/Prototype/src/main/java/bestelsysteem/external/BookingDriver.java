package bestelsysteem.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class BookingDriver implements HotelDriver {
    @Value("${booking.api.key}")
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
                .uri(URI.create("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchDestination?query="+encodedLocation))
                .header("x-rapidapi-key", apiKey)
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

        return response.body();
    }

    @Override
    public String getHotels(String locationCode) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotels?dest_id="+ locationCode +"&search_type=CITY&arrival_date=2025-05-01&departure_date=2025-05-10"))
                .header("x-rapidapi-key", apiKey)
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
        return response.body();
    }
}
