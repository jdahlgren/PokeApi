package se.johannesdahlgren.pokeapi.model;

import java.util.List;

public record Pokedex(int id, String name, boolean isMainSeries, Region region, List<PokemonEntry> pokemonEntries) {

  public Pokedex {
    pokemonEntries = List.copyOf(pokemonEntries);
  }
}
