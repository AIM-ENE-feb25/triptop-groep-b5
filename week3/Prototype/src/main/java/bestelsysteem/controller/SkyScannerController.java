package bestelsysteem.controller;

import bestelsysteem.service.port.AuthServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class SkyScannerController {

    @Value("${rapidapi.key}")
    private String rapidToken;

    @Value("${rapidapi.host}")
    private String rapidHost;

    @GetMapping("/handleRequest")
    public ResponseEntity<String> handleRequest(@RequestHeader("Authorization") String token,
                                                @RequestParam String apiName) throws IOException, InterruptedException {
        HttpRequest authRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/authorization/check-access?apiName=" + apiName))
                .header("Authorization", token)
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> authResponse = HttpClient.newHttpClient().send(authRequest, HttpResponse.BodyHandlers.ofString());

        if (authResponse.statusCode() != 200) {
            return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
        }

        // Als toegang is toegestaan, stuur de request door naar de externe API
        String apiUrl = switch (apiName.toLowerCase()) {
            case "skyscanner" -> "https://skyscanner89.p.rapidapi.com/flights/auto-complete?query=New";
            case "booking" -> "https://api.booking.com/...";
            default -> null;
        };

        if (apiUrl == null) {
            return new ResponseEntity<>("Invalid API Name", HttpStatus.BAD_REQUEST);
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("x-rapidapi-key", rapidToken)
                .header("x-rapidapi-host", rapidHost)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new ResponseEntity<>(response.body(), HttpStatus.OK);
    }
}