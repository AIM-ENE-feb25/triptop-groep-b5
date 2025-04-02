package bestelsysteem.controller;

import bestelsysteem.service.port.AuthServicePort;
import bestelsysteem.service.port.GatewayServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class GatewayController {
    private final GatewayServicePort gatewayServicePort;
    private final AuthServicePort authServicePort;

    @Autowired
    public GatewayController(GatewayServicePort gatewayServicePort, AuthServicePort authServicePort) {
        this.gatewayServicePort = gatewayServicePort;
        this.authServicePort = authServicePort;
    }

    @GetMapping("/handleRequest")
    public ResponseEntity<String> handleRequest(@RequestParam String token) {
        String authorizedUser = authServicePort.authorize(token);
        boolean hasAccess = gatewayServicePort.hasAccessToLegacyApi("beheerder");

        if (hasAccess && authorizedUser != null) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://sky-scanner3.p.rapidapi.com/flights/airports"))
                        .header("x-rapidapi-key", "ae4f671a76mshf0b5d8dd4515a3cp176dd3jsn9f9ab9d328cd")
                        .header("x-rapidapi-host", "sky-scanner3.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                return ResponseEntity.ok(response.body());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access!");
    }
}