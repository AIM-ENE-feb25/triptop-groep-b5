package bestelsysteem.service;

import bestelsysteem.model.LoginRequest;
import bestelsysteem.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WireMockService {

    private final RestTemplate restTemplate;

    @Autowired
    public WireMockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public LoginResponse login(LoginRequest request) {
        String url = "https://triptop-identity.wiremockapi.cloud/login";
        return restTemplate.postForObject(url, request, LoginResponse.class);
    }

    public boolean checkAppAccess(String token) {
        String url = "https://triptop-identity.wiremockapi.cloud/checkAppAccess";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("token", token);

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        // Assuming the response body contains the role information
        return response.getBody() != null && response.getBody().contains("beheerder");
    }
}