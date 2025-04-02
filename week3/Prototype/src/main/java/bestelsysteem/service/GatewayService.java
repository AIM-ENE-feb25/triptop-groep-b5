package bestelsysteem.service;

import bestelsysteem.service.port.GatewayServicePort;
import org.springframework.stereotype.Service;

@Service
public class GatewayService implements GatewayServicePort {

    @Override
    public boolean hasAccessToLegacyApi(String role) {
        return "admin".equalsIgnoreCase(role);
    }


}

