package se.johannesdahlgren.pokeapi.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import se.johannesdahlgren.pokeapi.client.PokeApiClient;
import se.johannesdahlgren.pokeapi.model.pokemon.species.PokemonSpecies;

@Service
public class PokemonService {

  private final PokeApiClient pokeApiClient;

  public PokemonService(PokeApiClient pokeApiClient) {
    this.pokeApiClient = pokeApiClient;
  }

  @Cacheable(value = "species")
  public PokemonSpecies getSpecies(String id) {
    return pokeApiClient.getSpecies(id);
  }
}
