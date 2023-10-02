package se.johannesdahlgren.pokeapi.service;

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

  public PokeApiList getPokeApiList() {
    return pokeApiClient.listPokedex(0, MAX_POKEDEXES);
  }

  public Pokedex getPokedex(String id) {
    return pokeApiClient.getPokedex(id);
  }
}
