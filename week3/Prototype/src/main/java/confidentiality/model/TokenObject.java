package confidentiality.model;

import java.util.logging.Logger;

public class TokenObject {
  private static final Logger logger = Logger.getLogger(TokenObject.class.getName());
  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}