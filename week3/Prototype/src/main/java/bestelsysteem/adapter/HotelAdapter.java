package bestelsysteem.adapter;

import bestelsysteem.model.Hotel;

import java.util.List;

public interface HotelAdapter {
    List<Hotel> getHotels();
    String getApiName();
}
