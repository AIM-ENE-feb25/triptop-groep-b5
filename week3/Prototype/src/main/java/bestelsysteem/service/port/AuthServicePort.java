package bestelsysteem.service.port;

import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;

public interface AuthServicePort {
    UserAccessInfo authorizeUser(UserAuthorization userAuthorization);
}
