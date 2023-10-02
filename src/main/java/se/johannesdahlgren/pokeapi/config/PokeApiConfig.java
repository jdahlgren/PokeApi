package se.johannesdahlgren.pokeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import se.johannesdahlgren.pokeapi.client.PokeApiClient;

@Configuration
public class PokeApiConfig {

  @Bean
  public PokeApiClient pokeApiClient(WebClient webClient) {
    return HttpServiceProxyFactory
        .builder(WebClientAdapter.forClient(webClient))
        .build()
        .createClient(PokeApiClient.class);
  }
}
