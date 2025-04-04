package bestelsysteem.service.port;

import bestelsysteem.model.UserAccessInfo;

public interface AuthorizationServicePort {
    UserAccessInfo hasAccess(String role);
}
