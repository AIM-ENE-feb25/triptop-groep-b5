package bestelsysteem.service.port;

public interface AuthorizationServicePort {
    boolean hasAccess(String token, String role);
}
