package bestelsysteem.controller;

import bestelsysteem.adapter.WireMockAdapter;
import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import bestelsysteem.service.port.AuthServicePort;
import bestelsysteem.service.port.AuthorizationServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class GatewayController {
    private final AuthorizationServicePort authorizationServicePort;
    private final AuthServicePort authServicePort;
    private final WireMockAdapter wireMockAdapter;

    @Value("${rapidapi.key}")
    private String tokenRapid;

    @Value("${rapidapi.host}")
    private String hostRapid;

    @Autowired
    public GatewayController(AuthorizationServicePort authorizationServicePort, AuthServicePort authServicePort, WireMockAdapter wireMockAdapter) {
        this.authorizationServicePort = authorizationServicePort;
        this.authServicePort = authServicePort;
        this.wireMockAdapter = wireMockAdapter;
    }

    @GetMapping("/request")
    public ResponseEntity<String> handleRequest(@RequestHeader("Authorization") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing, invalid, or expired Authorization token.");
        }

        UserAccessInfo userAccessInfo = new UserAccessInfo();
        userAccessInfo.setToken(token);

        try {
            UserAccessInfo accessInfo = authorizationServicePort.hasAccess("klant");
            if (!accessInfo.hasAccess() || !accessInfo.getRole().equals("klant")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied. User does not have the required role.");
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://skyscanner89.p.rapidapi.com/flights/complete/list"))
                    .header("x-rapidapi-key", tokenRapid)
                    .header("x-rapidapi-host", hostRapid)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body().contains("You are not subscribed to this API.")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not subscribed to this API.");
            }

            return ResponseEntity.ok(response.body());
        } catch (Exception e) {
            System.out.println("Error occurred while processing the request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }
}