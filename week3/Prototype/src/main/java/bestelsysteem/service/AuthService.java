package bestelsysteem.service;

import bestelsysteem.adapter.AuthAdapter;
import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServicePort {
  private final AuthAdapter authAdapter;

  @Autowired
  public AuthService(AuthAdapter authAdapter) {
    this.authAdapter = authAdapter;
  }

  @Override
  public UserAccessInfo authorizeUser(UserAuthorization userAuthorization) {
    String token = authAdapter.getToken(userAuthorization);
    userAuthorization.setToken(token);
    userAuthorization.setApplication("triptop");

    UserAccessInfo userAccessInfo = authAdapter.getRole(userAuthorization);
    userAccessInfo.setToken(token);
    return userAccessInfo;
  }

  @Override
  public UserAccessInfo authorizeUser(String token) {
    return null;
  }
}
