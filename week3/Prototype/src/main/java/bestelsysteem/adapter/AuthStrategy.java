package bestelsysteem.adapter;

import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;

public interface AuthStrategy {
//  String getToken(UserAuthorization userAuthorization);
  UserAccessInfo getRole(UserAuthorization userAuthorization);
}
