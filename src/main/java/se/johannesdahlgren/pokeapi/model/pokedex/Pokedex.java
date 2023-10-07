package se.johannesdahlgren.pokeapi.model.pokedex;

import java.util.List;

public record Pokedex(int id, String name, boolean isMainSeries, RegionNameUrl region, List<PokemonEntry> pokemonEntries) {

  public Pokedex {
    pokemonEntries = List.copyOf(pokemonEntries);
  }

  public record RegionNameUrl(String name, String url) {

  }
}
