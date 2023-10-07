package domain.service;

import dao.DaoPokemon;
import domain.modelo.pokemon.Pokemon;
import domain.modelo.pokemon.Result;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class PokemonService {

    private final DaoPokemon daoPokemon;

    @Inject
    public PokemonService(DaoPokemon daoPokemon) {
        this.daoPokemon = daoPokemon;
    }

    public Either<String, List<Result>> getAllPokemonsIds() {
        return Either.right(daoPokemon.getAllPokemonsIds().get());
    }

    public Either<String, List<Result>> getAllPokemonsIdsFiltered(String nombreABuscar) {
        List<Result> listaEnteraPokemons = daoPokemon.getAllPokemonsIds().get();
        List<Result> listaConPokemonsNombreSimilar = listaEnteraPokemons.stream().filter(result -> result.getName().contains(nombreABuscar)).toList();

        if (listaConPokemonsNombreSimilar.isEmpty()) {
            return Either.left("No hay Pokemons");
        } else {
            return Either.right(listaConPokemonsNombreSimilar);
        }
    }

    public Either<String, Pokemon> getPokemonById(Integer idPokemon) {
        return daoPokemon.getPokemonsById(idPokemon);
    }
}
