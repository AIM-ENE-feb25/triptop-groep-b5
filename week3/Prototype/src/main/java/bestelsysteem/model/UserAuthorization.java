package bestelsysteem.model;

public class UserAuthorization {
  String username;
  String password;
  String token;
  String application;
  String role;

  public UserAuthorization() {}

  public UserAuthorization(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public UserAuthorization(String username, String password, String token) {
    this.username = username;
    this.password = password;
    this.token = token;
  }

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

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
