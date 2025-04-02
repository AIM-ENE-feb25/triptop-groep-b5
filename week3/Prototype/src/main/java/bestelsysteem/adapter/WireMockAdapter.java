package bestelsysteem.adapter;

import bestelsysteem.exception.APICallException;
import bestelsysteem.external.WireMockDriver;
import bestelsysteem.model.ErrorObject;
import bestelsysteem.model.TokenObject;
import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;

@Service
public class WireMockAdapter implements AuthStrategy {
  private final WireMockDriver wireMockAPI;
  private final ObjectMapper objectMapper;

  public WireMockAdapter() {
    wireMockAPI = new WireMockDriver();
    objectMapper = new ObjectMapper();
  }

  public UserAccessInfo getRole(UserAuthorization userAuthorization) {
    try {
      String loginResponse = wireMockAPI.login(userAuthorization.getUsername(), userAuthorization.getPassword());

      TokenObject tokenObject = objectMapper.readValue(loginResponse, TokenObject.class);
      String token = tokenObject.getToken();

      String checkAppAccessResponse = wireMockAPI.checkAppAccess(userAuthorization.getUsername(), userAuthorization.getApplication(), token);

      return objectMapper.readValue(checkAppAccessResponse, UserAccessInfo.class);
    } catch (RestClientResponseException e) {
      throw new APICallException(new ErrorObject(e.getMessage(), e.getClass().getSimpleName(), "Username or password is incorrect."));
    } catch (IOException e) {
      throw new APICallException(new ErrorObject(e.getMessage(), e.getClass().getSimpleName(), "Unable to get token. Try again later."));
    }
  }
}
