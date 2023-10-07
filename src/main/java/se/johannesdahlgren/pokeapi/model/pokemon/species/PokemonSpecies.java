package se.johannesdahlgren.pokeapi.model.pokemon.species;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

public record PokemonSpecies(int id,
                             String name,
                             boolean isBaby,
                             boolean isLegendary,
                             boolean isMythical,
                             @JsonAlias("pokedex_numbers")
                             List<PokemonSpeciesDexEntry> pokedexes,
                             @JsonAlias("evolves_from_species")
                             EvolvesFromNameUrl evolvesFrom) {

  public PokemonSpecies {
    pokedexes = List.copyOf(pokedexes);
  }

  public record EvolvesFromNameUrl(String name, String url) {

  }
}
