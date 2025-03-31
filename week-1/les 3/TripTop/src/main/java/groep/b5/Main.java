package groep.b5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class Main {
    private static final String URL = "https://triptop-identity.wiremockapi.cloud/login";
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(getCredentionals()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCredentionals() {
        final String username = "edevries";
        final String password = "3g2Rw9sT1x";
        return "{ \"username\":" + username + ", \"password\": " + password + "}";
    }
}