package bestelsysteem.adapter;

import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;

public interface AuthAdapter {
  String getToken(UserAuthorization userAuthorization);
  UserAccessInfo getRole(UserAuthorization userAuthorization);
}
