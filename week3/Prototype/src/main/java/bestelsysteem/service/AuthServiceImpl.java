package bestelsysteem.service;

import bestelsysteem.adapter.AuthAdapter;
import bestelsysteem.exception.APICallException;
import bestelsysteem.model.ErrorObject;
import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import bestelsysteem.service.port.AuthServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServicePort {
  private final AuthAdapter authAdapter;

  @Autowired
  public AuthServiceImpl(AuthAdapter authAdapter) {
    this.authAdapter = authAdapter;
  }

  @Override
  public UserAccessInfo authorizeUser(UserAuthorization userAuthorization) {
    String token = authAdapter.getToken(userAuthorization);

    userAuthorization.setToken(token);
    userAuthorization.setApplication("triptop");

    UserAccessInfo userAccessInfo = authAdapter.getRole(userAuthorization);
    userAccessInfo.setToken(token);
    userAuthorization.setRole(userAccessInfo.getRole());

    return userAccessInfo;
  }
}
