package bestelsysteem.model;

public class UserAuthorization {
  private String username;
  private String password;
  private String token;
  private String role;
  private String application = "triptop";

  // Getters and setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

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
        this.role = role;
    }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }
}