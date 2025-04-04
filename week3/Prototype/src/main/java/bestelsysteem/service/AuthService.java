package bestelsysteem.service;

import bestelsysteem.adapter.AuthStrategy;
import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServicePort {
  private final AuthStrategy authAdapter;

  @Autowired
  public AuthService(AuthStrategy authAdapter) {
    this.authAdapter = authAdapter;
  }

  @Override
  public UserAccessInfo authorizeUser(UserAuthorization userAuthorization) {
    return authAdapter.getRole(userAuthorization);
  }

  @Override
  public UserAccessInfo authorizeUser(String token) {
    return null;
  }
}
