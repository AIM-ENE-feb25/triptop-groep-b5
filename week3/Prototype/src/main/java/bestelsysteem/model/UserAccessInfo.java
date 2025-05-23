package bestelsysteem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class UserAccessInfo {
  String access;
  String role;
  String token;

  public boolean hasAccess() {
    return Objects.equals(access, "allowed");
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
