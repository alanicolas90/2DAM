package domain.service;

import common.constantes.Constantes;
import dao.DaoPokemon;
import domain.modelo.pokemon.Pokemon;
import domain.modelo.pokemon.Result;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class PokemonService {

    private final DaoPokemon daoPokemonImpl;

    @Inject
    public PokemonService(DaoPokemon daoPokemonImpl) {
        this.daoPokemonImpl = daoPokemonImpl;
    }

    public Either<String, List<Result>> getAllPokemonsIds() {
        return Either.right(daoPokemonImpl.getAllPokemonsIds().get());
    }

    public Either<String, List<Result>> getAllPokemonsIdsFiltered(String nombreABuscar) {
        List<Result> listaEnteraPokemons = daoPokemonImpl.getAllPokemonsIds().get();
        List<Result> listaConPokemonsNombreSimilar = listaEnteraPokemons.stream().filter(result -> result.getName().contains(nombreABuscar)).toList();

        if (listaConPokemonsNombreSimilar.isEmpty()) {
            return Either.left(Constantes.NO_HAY_POKEMONS);
        } else {
            return Either.right(listaConPokemonsNombreSimilar);
        }
    }

    public Either<String, Pokemon> getPokemonById(Integer idPokemon) {
        return daoPokemonImpl.getPokemonsById(idPokemon);
    }
}
