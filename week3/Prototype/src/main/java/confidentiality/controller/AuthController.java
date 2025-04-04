package confidentiality.controller;

import confidentiality.model.UserAccessInfo;
import confidentiality.model.UserAuthorization;
import confidentiality.service.port.AuthServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  AuthServicePort authService;

  @Autowired
  public AuthController(AuthServicePort authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<UserAccessInfo> login(@RequestBody UserAuthorization userAuthorization) {
    UserAccessInfo userAccessInfo = authService.authorizeUser(userAuthorization);

    return ResponseEntity.ok(userAccessInfo);
  }
}
