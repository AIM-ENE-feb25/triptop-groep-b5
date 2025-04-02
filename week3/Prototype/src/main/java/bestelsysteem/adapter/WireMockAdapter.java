package bestelsysteem.adapter;

import bestelsysteem.exception.APICallException;
import bestelsysteem.model.ErrorObject;
import bestelsysteem.model.TokenObject;
import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.el.parser.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Objects;

@Service
public class WireMockAdapter implements AuthAdapter {

  @Override
  public String getToken(UserAuthorization userAuthorization) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

      HttpEntity<UserAuthorization> request = new HttpEntity<>(userAuthorization, headers);
      RestTemplate restTemplate = new RestTemplate();
      String resourceUrl = "http://identity-wiremock.minordevops.nl:8080/login";
      String response =
              restTemplate.postForObject(resourceUrl, request, String.class);

      ObjectMapper objectMapper = new ObjectMapper();
      TokenObject tokenObject = objectMapper.readValue(response, TokenObject.class);
      return tokenObject.getToken();
    } catch (RestClientResponseException e) {
      throw new APICallException(new ErrorObject(e.getMessage(), e.getClass().getSimpleName(), "Authentication provider is current unavailable. Try again later."));
    } catch (IOException e) {
      throw new APICallException(new ErrorObject(e.getMessage(), e.getClass().getSimpleName(), "Unable to get token. Try again later."));
    }
  }

  @Override
  public UserAccessInfo getRole(UserAuthorization userAuthorization) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

      HttpEntity<UserAuthorization> request = new HttpEntity<>(userAuthorization, headers);
      RestTemplate restTemplate = new RestTemplate();
      String resourceUrl = "http://identity-wiremock.minordevops.nl:8080/checkAppAccess?token=" + userAuthorization.getToken();

      String response = restTemplate.postForObject(resourceUrl, request, String.class);

      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(response, UserAccessInfo.class);
    } catch (RestClientResponseException e) {
      throw new APICallException(new ErrorObject(e.getMessage(), e.getClass().getSimpleName(), "Authentication provider is current unavailable. Try again later."));
    } catch (IOException e) {
      throw new APICallException(new ErrorObject(e.getMessage(), e.getClass().getSimpleName(), "Unable to get token. Try again later."));
    }
  }
}
