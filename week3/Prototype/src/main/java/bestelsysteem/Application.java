package bestelsysteem;

import bestelsysteem.adapter.BookingApiAdapter;
import bestelsysteem.adapter.TripAdvisorApiAdapter;
import bestelsysteem.model.Hotel;
import bestelsysteem.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application {
    private static final Logger LOG = LoggerFactory
            .getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(Application.class, args);
        LOG.info("APPLICATION FINISHED");

        HotelService hotelService = new HotelService();
        List<Hotel> hotels = hotelService.getHotels();
        for (Hotel hotel : hotels) {
            System.out.println(hotel.getName());
        }
    }
}