package bestelsysteem.adapter;

import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;

public interface AuthAdapter {
    UserAccessInfo getRole(UserAuthorization userAuthorization);
}
