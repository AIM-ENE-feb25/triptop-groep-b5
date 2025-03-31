package bestelsysteem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReisController {

    @GetMapping("/hotels")
    public ResponseEntity<String> getHotels() {
        return ResponseEntity.ok("hotels");
    }
}
