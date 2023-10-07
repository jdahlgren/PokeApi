package se.johannesdahlgren.pokeapi.model.pokedex;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

public record PokedexList(int count, String next, String previous, @JsonAlias("results") List<PokedexListResult> pokedexListResults) {

  public PokedexList {
    pokedexListResults = List.copyOf(pokedexListResults);
  }

  public record PokedexListResult(String name, String url) {

  }
}
