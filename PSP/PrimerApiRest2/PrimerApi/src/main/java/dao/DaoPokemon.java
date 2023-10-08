package dao;

import domain.modelo.pokemon.Pokemon;
import domain.modelo.pokemon.Result;
import io.vavr.control.Either;

import java.util.List;

public interface DaoPokemon {
    Either<String, Pokemon> getPokemonsById(Integer pokedexNum);

    Either<String, List<Result>> getAllPokemonsIds();
}
