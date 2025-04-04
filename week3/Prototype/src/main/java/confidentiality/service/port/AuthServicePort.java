package confidentiality.service.port;

import confidentiality.model.UserAccessInfo;
import confidentiality.model.UserAuthorization;

public interface AuthServicePort {
    UserAccessInfo authorizeUser(UserAuthorization userAuthorization);
}
