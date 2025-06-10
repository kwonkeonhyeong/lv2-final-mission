package finalmission.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.util.Base64;

@Configuration
public class ClientConfig {
    @Bean
    public RestClient createPaymentClient() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5_000);
        factory.setReadTimeout(5_000);

        return RestClient.builder()
                .baseUrl("https://randommer.io")
                .defaultHeader("X-Api-Key", "735cf8dca19e4fd38c5d57ccb45589c6")
                .requestFactory(factory)
                .build();
    }
}
