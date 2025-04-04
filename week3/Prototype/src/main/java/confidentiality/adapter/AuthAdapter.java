package confidentiality.adapter;

import confidentiality.model.UserAccessInfo;
import confidentiality.model.UserAuthorization;

public interface AuthAdapter {
    UserAccessInfo getRole(UserAuthorization userAuthorization);
}
