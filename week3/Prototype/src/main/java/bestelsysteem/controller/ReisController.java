package bestelsysteem.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReisController {

    @Value("${tripadvisor.api.key}")
    private String apiKey;

    @GetMapping("/hotels")
    public ResponseEntity<String> getHotels() {
        System.out.println(apiKey);
        return ResponseEntity.ok("hotels");
    }
}
