package se.johannesdahlgren.pokeapi.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import se.johannesdahlgren.pokeapi.model.pokedex.PokedexList;
import se.johannesdahlgren.pokeapi.model.pokedex.Pokedex;

@HttpExchange
public interface PokeApiClient {

  @GetExchange("/pokedex/")
  PokedexList listPokedex(@RequestParam int offset, @RequestParam int limit);

  @GetExchange("pokedex/{id}")
  Pokedex getPokedex(@PathVariable String id);

}
