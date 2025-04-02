package bestelsysteem.model;

public class LoginResponse {
    private String token;
    private String username;

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public void setToken(String token) {
        this.token = token;
    }
}