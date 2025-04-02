package bestelsysteem.service;

import bestelsysteem.model.LoginRequest;
import bestelsysteem.service.port.AuthServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServicePort {

    private final WireMockService wireMockService;

    @Autowired
    public AuthService(WireMockService wireMockService) {
        this.wireMockService = wireMockService;
    }

    @Override
    public String authenticate(String username, String password) {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        var response = wireMockService.login(request);
        return response != null ? response.getToken() : null;
    }

    @Override
    public String authorize(String token) {
        return wireMockService.checkAppAccess(token) ? "beheerder" : null;
    }
}