package confidentiality.service;

import confidentiality.adapter.WireMockAdapter;
import confidentiality.model.UserAccessInfo;
import confidentiality.model.UserAuthorization;
import confidentiality.service.port.AuthorizationServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationServicePort {

    private final WireMockAdapter wireMockAdapter;

    @Autowired
    public AuthorizationServiceImpl(WireMockAdapter wireMockAdapter) {
        this.wireMockAdapter = wireMockAdapter;
    }

    @Override
    public UserAccessInfo hasAccess(String role) {
        UserAuthorization userAuthorization = new UserAuthorization();
        userAuthorization.setUsername("edevries");
        userAuthorization.setPassword("3g2Rw9sT1x");
        userAuthorization.setApplication(userAuthorization.getApplication());

        UserAccessInfo userAccessInfo = wireMockAdapter.getRole(userAuthorization);

        if (userAccessInfo.getRole().equals(role)) {
            return userAccessInfo;
        } else {
            return userAccessInfo;
        }
    }
}