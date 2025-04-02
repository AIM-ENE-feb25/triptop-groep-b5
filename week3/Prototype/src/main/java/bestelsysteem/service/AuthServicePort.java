package bestelsysteem.service;

import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;

public interface AuthServicePort {
  UserAccessInfo authorizeUser(UserAuthorization userAuthorization);
  UserAccessInfo authorizeUser(String token);
}
