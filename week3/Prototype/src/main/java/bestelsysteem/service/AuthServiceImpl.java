package bestelsysteem.service;

import bestelsysteem.adapter.AuthAdapter;
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

    return authAdapter.getRole(userAuthorization);
  }

}
