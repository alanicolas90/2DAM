package dao;

import domain.modelo.pokemon.Pokemon;
import domain.modelo.pokemon.PokemonResponse;
import domain.modelo.pokemon.Result;
import domain.retrofit.PokemonApi;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

@Log4j2
public class DaoPokemon {

    private final RetroFit retroFit;

    @Inject
    public DaoPokemon(RetroFit retroFit) {
        this.retroFit = retroFit;
    }

    public Either<String, Pokemon> getPokemonsById(Integer pokedexNum) {
        Either<String, Pokemon> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        PokemonApi api = retrofit.create(PokemonApi.class);

        try {
            Response<Pokemon> pokemonResponse = api.getPokemon(pokedexNum).execute();
            if (pokemonResponse.isSuccessful() && pokemonResponse.body() != null) {
                resultado = Either.right(pokemonResponse.body());
            } else {
                resultado = Either.left(pokemonResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }

        return resultado;
    }

    public Either<String, List<Result>> getAllPokemonsIds(){
        Either<String, List<Result>> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        PokemonApi api = retrofit.create(PokemonApi.class);

        try {
            Response<PokemonResponse> pokemonResponse = api.getAllPokemonsIds(10000).execute();
            if (pokemonResponse.isSuccessful() && pokemonResponse.body() != null) {
                resultado = Either.right(pokemonResponse.body().getResults());
            } else {
                resultado = Either.left(pokemonResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }

        return resultado;

    }
}
