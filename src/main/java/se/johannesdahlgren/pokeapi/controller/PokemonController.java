package se.johannesdahlgren.pokeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.johannesdahlgren.pokeapi.model.pokemon.species.PokemonSpecies;
import se.johannesdahlgren.pokeapi.service.PokemonService;

@RestController
@RequestMapping("pokemon")
public class PokemonController {

  private final PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @GetMapping("species/{id}")
  public PokemonSpecies getSpecies(@PathVariable String id){
    return pokemonService.getSpecies(id);
  }
}
