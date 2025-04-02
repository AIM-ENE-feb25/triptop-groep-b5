package bestelsysteem.service.port;

public interface AuthServicePort {
    String authenticate(String username, String password);
    String authorize(String token);
}

