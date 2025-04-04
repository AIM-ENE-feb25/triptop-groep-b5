package confidentiality.adapter;

import confidentiality.exception.APICallException;
import confidentiality.external.WireMockDriver;
import confidentiality.model.ErrorObject;
import confidentiality.model.TokenObject;
import confidentiality.model.UserAccessInfo;
import confidentiality.model.UserAuthorization;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;

@Service
public class WireMockAdapter implements AuthAdapter {
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

            UserAccessInfo userAccessInfo = objectMapper.readValue(checkAppAccessResponse, UserAccessInfo.class);
            userAccessInfo.setToken(token);


            return userAccessInfo;
        } catch (RestClientResponseException e) {
            throw new APICallException(new ErrorObject(e.getMessage(), e.getClass().getSimpleName(), "Username or password is incorrect."));
        } catch (IOException e) {
            throw new APICallException(new ErrorObject(e.getMessage(), e.getClass().getSimpleName(), "Unable to get token. Try again later."));
        }
    }
}