package bestelsysteem.controller;

import bestelsysteem.model.UserAccessInfo;
import bestelsysteem.model.UserAuthorization;
import bestelsysteem.service.AuthServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
  AuthServicePort authService;

  @Autowired
  public AuthController(AuthServicePort authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public UserAccessInfo login(@RequestBody UserAuthorization userAuthorization) {
    UserAccessInfo userAccessInfo = authService.authorizeUser(userAuthorization);
    if (!userAccessInfo.hasAccess()) {
      throw new RuntimeException("Unauthorized");
    }

    return userAccessInfo;
  }
}
