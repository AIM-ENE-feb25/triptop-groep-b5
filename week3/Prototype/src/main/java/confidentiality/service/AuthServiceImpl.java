package confidentiality.service;

import confidentiality.adapter.AuthAdapter;
import confidentiality.model.UserAccessInfo;
import confidentiality.model.UserAuthorization;
import confidentiality.service.port.AuthServicePort;
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
