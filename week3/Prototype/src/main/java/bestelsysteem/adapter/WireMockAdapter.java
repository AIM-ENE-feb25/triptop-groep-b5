package bestelsysteem.adapter;

import bestelsysteem.model.TokenObject;
import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.el.parser.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Service
public class WireMockAdapter implements AuthAdapter {

  @Override
  public String getToken(UserAuthorization userAuthorization) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    HttpEntity<UserAuthorization> request = new HttpEntity<>(userAuthorization, headers);
    RestTemplate restTemplate = new RestTemplate();
    String resourceUrl = "http://identity-wiremock.minordevops.nl:8080/login";
    ResponseEntity<String> response =
            restTemplate.postForEntity(resourceUrl, request, String.class);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      TokenObject tokenObject = objectMapper.readValue(response.getBody(), TokenObject.class);
      return tokenObject.getToken();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to parse token");
    }
  }

  @Override
  public UserAccessInfo getRole(UserAuthorization userAuthorization) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    HttpEntity<UserAuthorization> request = new HttpEntity<>(userAuthorization, headers);
    RestTemplate restTemplate = new RestTemplate();
    String resourceUrl = "http://identity-wiremock.minordevops.nl:8080/checkAppAccess?token=" + userAuthorization.getToken();
    ResponseEntity<String> response = restTemplate.postForEntity(resourceUrl, request, String.class);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(response.getBody(), UserAccessInfo.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to parse information");
    }
  }
}
