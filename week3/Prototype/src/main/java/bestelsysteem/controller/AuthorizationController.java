package bestelsysteem.controller;

import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import bestelsysteem.service.port.AuthServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorizationController {

    private final AuthServicePort authServicePort;

    @Autowired
    public AuthorizationController(AuthServicePort authServicePort) {
        this.authServicePort = authServicePort;
    }

    @PostMapping("/authorize-user")
    public ResponseEntity<Boolean> authorizeUser(@RequestHeader("Authorization") String token,
                                                 @RequestParam String apiName) {
        UserAuthorization userAuthorization = new UserAuthorization();
        userAuthorization.setToken(token);
        UserAccessInfo userAccessInfo = authServicePort.authorizeUser(userAuthorization);

        boolean hasAccess = switch (apiName.toLowerCase()) {
            case "skyscanner" -> userAccessInfo.getRole().equalsIgnoreCase("beheerder");
            case "booking" -> userAccessInfo.getRole().equalsIgnoreCase("klant");
            default -> false;
        };

        return hasAccess ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
    }
}