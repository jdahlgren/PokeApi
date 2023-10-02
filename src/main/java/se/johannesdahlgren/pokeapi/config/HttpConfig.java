package se.johannesdahlgren.pokeapi.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.netty.resolver.DefaultAddressResolverGroup;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
@EnableCaching
public class HttpConfig {

  @Value("${pokeapi.base-uri}")
  private String baseUrl;
  @Value("${pokeapi.max-buffer-size}")
  private int maxBufferSize;

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public Jackson2JsonDecoder jsonDecoder(ObjectMapper objectMapper) {
    ObjectMapper mapper = objectMapper.copy();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON);
  }

  @Bean
  public Jackson2JsonEncoder jsonEncoder(ObjectMapper objectMapper) {
    ObjectMapper mapper = objectMapper.copy();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    return new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON);
  }

  @Bean
  public ExchangeStrategies exchangeStrategies(Jackson2JsonDecoder decoder, Jackson2JsonEncoder encoder) {
    return ExchangeStrategies.builder()
        .codecs(clientDefaultCodecsConfigurer -> {
          clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(encoder);
          clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(decoder);
        })
        .build();
  }

  @Bean
  public WebClient webClient(HttpClient httpClient, ExchangeStrategies exchangeStrategies) {
    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .baseUrl(baseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchangeStrategies(exchangeStrategies)
        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(maxBufferSize))
        .filter(requestLoggingFilter())
        .build();
  }

  @Bean
  public ConnectionProvider connectionProvider() {
    return ConnectionProvider.builder("Auto refresh & no connection limit")
        .maxIdleTime(Duration.ofSeconds(10))
        .maxConnections(500)
        .pendingAcquireMaxCount(-1)
        .build();
  }

  @Bean
  public HttpClient httpClient(ConnectionProvider connectionProvider) {
    return HttpClient.create(connectionProvider)
        .compress(true)
        .resolver(DefaultAddressResolverGroup.INSTANCE);
  }

  private ExchangeFilterFunction requestLoggingFilter() {
    return ExchangeFilterFunction.ofRequestProcessor(request -> {
      Logger log = LoggerFactory.getLogger(HttpConfig.class);
      log.info("{} {}", request.method(), request.url());
      return Mono.just(request);
    });
  }
}
