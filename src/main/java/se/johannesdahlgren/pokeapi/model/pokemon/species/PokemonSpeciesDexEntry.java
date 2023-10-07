package se.johannesdahlgren.pokeapi.model.pokemon.species;

public record PokemonSpeciesDexEntry(int entryNumber, PokedexNameUrl pokedex) {

  public record PokedexNameUrl(String name, String url) {

  }
}
