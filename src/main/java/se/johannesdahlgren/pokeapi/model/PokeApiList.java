package se.johannesdahlgren.pokeapi.model;

import java.util.List;

public record PokeApiList(int count, String next, String previous, List<Result> results) {

  public PokeApiList {
    results = List.copyOf(results);
  }

  public record Result(String name, String url) {

  }
}
