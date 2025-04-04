package confidentiality.external;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class WireMockDriver {
    public String login(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String json = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://identity-wiremock.minordevops.nl:8080/login";
        return restTemplate.postForObject(resourceUrl, request, String.class);
    }

    public String checkAppAccess(String username, String application, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String json = String.format("{\"username\":\"%s\", \"application\":\"%s\"}", username, application);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://identity-wiremock.minordevops.nl:8080/checkAppAccess?token=" + token;
        return restTemplate.postForObject(resourceUrl, request, String.class);
    }
}