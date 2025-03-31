package bestelsysteem.adapter;

import bestelsysteem.model.Hotel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BookingApiAdapter implements HotelAdapter {
    @Override
    public List<Hotel> getHotels() {
        return null;
    }

    @Override
    public String getApiName() {
        return "Booking";
    }
}
