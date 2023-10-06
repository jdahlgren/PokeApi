package se.johannesdahlgren.pokeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.johannesdahlgren.pokeapi.model.PokeApiList;
import se.johannesdahlgren.pokeapi.model.Pokedex;
import se.johannesdahlgren.pokeapi.service.PokedexService;

@RestController
@RequestMapping("pokedex")
public class PokedexController {

  private final PokedexService pokedexService;

  public PokedexController(PokedexService pokeApiService) {
    this.pokedexService = pokeApiService;
  }

  @GetMapping
  public PokeApiList listPokedex() {
    return pokedexService.listPokedex();
  }

  @GetMapping("/{id}")
  public Pokedex getPokedex(@PathVariable String id) {
    return pokedexService.getPokedex(id);
  }
}
