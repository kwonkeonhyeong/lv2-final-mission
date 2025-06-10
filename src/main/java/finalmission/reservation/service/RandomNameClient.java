package finalmission.reservation.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RandomNameClient {
    private final RestClient restClient;

    public RandomNameClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getRandomName() {
        String type = "firstname";
        int quantity = 1;
        String response = restClient.get()
                .uri(String.format("https://randommer.io/api/Name?nameType=%s&quantity=%d", type, quantity))
                .retrieve()
                .body(String.class);

        return response.substring(2, response.length()-2);
    }
}
