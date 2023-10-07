package se.johannesdahlgren.pokeapi.model.pokedex;

public record PokemonEntry(int entryNumber, PokemonSpeciesNameUrl pokemonSpecies) {

  public record PokemonSpeciesNameUrl(String name, String url) {

  }
}
