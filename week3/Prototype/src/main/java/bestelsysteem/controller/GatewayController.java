package bestelsysteem.controller;

import bestelsysteem.model.UserAuthorization;
import bestelsysteem.service.port.AuthServicePort;
import bestelsysteem.service.port.AuthorizationServicePort;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public GatewayController(AuthorizationServicePort authorizationServicePort, AuthServicePort authServicePort) {
        this.authorizationServicePort = authorizationServicePort;
        this.authServicePort = authServicePort;
    }

    @GetMapping("/request")
    public ResponseEntity<String> handleRequest(@RequestHeader("Authorization") String token) {
        UserAuthorization userAuthorization = new UserAuthorization();
        userAuthorization.setToken(token);
        userAuthorization.setApplication("triptop");

        String authorizedUser = String.valueOf(authServicePort.authorizeUser(userAuthorization));
        boolean hasAccess = authorizationServicePort.hasAccess(token, "klant");

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