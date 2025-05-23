package bestelsysteem.controller;

import bestelsysteem.model.Hotel;
import bestelsysteem.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReisController {

    @Autowired
    HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<String>> getHotels(@RequestParam("location") String location) {
        List<Hotel> hotels = hotelService.getHotels(location);

        List<String> hotelNames = hotels.stream()
                .map(Hotel::getName)
                .collect(Collectors.toList());

        return ResponseEntity.ok(hotelNames);
    }
}
