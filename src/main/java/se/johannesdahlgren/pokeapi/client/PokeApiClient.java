package se.johannesdahlgren.pokeapi.client;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import se.johannesdahlgren.pokeapi.model.PokeApiList;
import se.johannesdahlgren.pokeapi.model.Pokedex;

@HttpExchange
public interface PokeApiClient {

  @GetExchange("/pokedex/")
  @Cacheable(value = "pokedexList")
  PokeApiList listPokedex(@RequestParam int offset, @RequestParam int limit);

  @GetExchange("pokedex/{id}")
  @Cacheable(value = "pokedex")
  Pokedex getPokedex(@PathVariable String id);

}
