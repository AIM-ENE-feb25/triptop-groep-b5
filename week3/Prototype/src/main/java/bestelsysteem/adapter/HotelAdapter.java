package bestelsysteem.adapter;

import bestelsysteem.model.Hotel;

import java.util.List;

public interface HotelAdapter {
    String getLocation(String location);
    List<Hotel> getHotels(String locationCode);
    String getApiName();
}
