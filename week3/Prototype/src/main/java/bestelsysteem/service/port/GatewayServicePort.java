package bestelsysteem.service.port;

public interface GatewayServicePort {
    boolean hasAccessToLegacyApi(String role);
}
