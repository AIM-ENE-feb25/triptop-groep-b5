package han.soex.prototype.controller;

import han.soex.prototype.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${api.wiremock.url}")
    private String wiremockApiUrl;

    private final RestTemplate restTemplate;

    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String url = wiremockApiUrl + "/login";
        ResponseEntity<String> response = restTemplate.postForEntity(url, user, String.class);
        return response;
    }

}
