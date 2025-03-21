package groep.b5.prototype;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UberService {

	public void getUberProducts() throws IOException, InterruptedException{
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://t14ha70d-uber-v1.p.rapidapi.com/v1/products"))
					.header("x-rapidapi-key", "ae4f671a76mshf0b5d8dd4515a3cp176dd3jsn9f9ab9d328cd")
					.header("x-rapidapi-host", "t14ha70d-uber-v1.p.rapidapi.com")
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

			System.out.println(response.body());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
