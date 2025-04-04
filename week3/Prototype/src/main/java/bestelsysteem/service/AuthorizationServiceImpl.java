package bestelsysteem.service;

import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import bestelsysteem.service.port.AuthorizationServicePort;
import bestelsysteem.adapter.AuthAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationServicePort {

    private final AuthAdapter authAdapter;

    @Autowired
    public AuthorizationServiceImpl(AuthAdapter authAdapter) {
        this.authAdapter = authAdapter;
    }

    @Override
    public boolean hasAccess(String token, String role) {
        UserAuthorization userAuthorization = new UserAuthorization();
        userAuthorization.setToken(token);
        userAuthorization.setApplication("triptop");

        UserAccessInfo userAccessInfo = authAdapter.getRole(userAuthorization);

        return userAccessInfo != null && role.equals(userAccessInfo.getRole());
    }
}
