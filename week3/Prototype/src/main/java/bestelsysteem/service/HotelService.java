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
    HotelAdapter TripAdvisorApiAdapter;
    HotelAdapter bookingApiAdapter;

    public List<Hotel> getHotels(String locatie) {
        TripAdvisorApiAdapter = new TripAdvisorApiAdapter();
        bookingApiAdapter = new BookingApiAdapter();
        List<Hotel> hotels = TripAdvisorApiAdapter.getHotels(TripAdvisorApiAdapter.getLocation(locatie));
        List<Hotel> hotels2 = bookingApiAdapter.getHotels(bookingApiAdapter.getLocation(locatie));

        System.out.println("TripAdvisor hotels: " + hotels.size());
        System.out.println("Booking hotels: " + hotels2.size());
        if(hotels.size() > hotels2.size()) {
            System.out.println(TripAdvisorApiAdapter.getApiName());
            return hotels;
        } else {
            System.out.println(bookingApiAdapter.getApiName());
            return hotels2;
        }
    }

}
