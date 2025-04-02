package bestelsysteem.service;

import bestelsysteem.adapter.BookingApiAdapter;
import bestelsysteem.adapter.HotelAdapter;
import bestelsysteem.adapter.TripAdvisorApiAdapter;
import bestelsysteem.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private List<HotelAdapter> hotelAdapters;

    public List<Hotel> getHotels(String locatie) {
        List<Hotel> bestHotels = new ArrayList<>();
        String bestApiName = "";

        for (HotelAdapter adapter : hotelAdapters) {
            List<Hotel> hotels = adapter.getHotels(adapter.getLocation(locatie));
            System.out.println(adapter.getApiName() + " hotels: " + hotels.size());

            if (hotels.size() > bestHotels.size()) {
                bestHotels = hotels;
                bestApiName = adapter.getApiName();
            }
        }

        System.out.println(bestApiName);
        return bestHotels;
    }
}
