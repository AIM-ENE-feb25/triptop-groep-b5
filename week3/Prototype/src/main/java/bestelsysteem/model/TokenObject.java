package bestelsysteem.model;

import java.util.logging.Logger;

public class TokenObject {
  private static final Logger logger = Logger.getLogger(TokenObject.class.getName());
  private String token;
  private String role;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    logger.info("Setting role: " + role);
    this.role = role;
  }
}