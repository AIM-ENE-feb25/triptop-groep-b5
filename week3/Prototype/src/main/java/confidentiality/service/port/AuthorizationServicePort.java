package confidentiality.service.port;

import confidentiality.model.UserAccessInfo;

public interface AuthorizationServicePort {
    UserAccessInfo hasAccess(String role);
}
