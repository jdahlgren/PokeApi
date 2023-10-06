package se.johannesdahlgren.pokeapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import se.johannesdahlgren.pokeapi.client.PokeApiClient;
import se.johannesdahlgren.pokeapi.model.PokeApiList;
import se.johannesdahlgren.pokeapi.model.Pokedex;

@Service
public class PokedexService {

  private final PokeApiClient pokeApiClient;
  private static final int MAX_POKEDEXES = 50;

  public PokedexService(PokeApiClient pokeApiClient) {
    this.pokeApiClient = pokeApiClient;
  }

  @Cacheable(value = "pokedexList")
  public PokeApiList listPokedex() {
    return pokeApiClient.listPokedex(0, MAX_POKEDEXES);
  }

  @Cacheable(value = "pokedex")
  public Pokedex getPokedex(String id) {
    return pokeApiClient.getPokedex(id);
  }
}
