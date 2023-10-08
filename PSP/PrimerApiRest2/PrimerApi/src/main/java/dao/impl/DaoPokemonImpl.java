package dao.impl;

import dao.DaoPokemon;
import dao.RetroFit;
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
import java.util.Arrays;
import java.util.List;

@Log4j2
public class DaoPokemonImpl implements DaoPokemon {

    private final RetroFit retroFit;

    @Inject
    public DaoPokemonImpl(RetroFit retroFit) {
        this.retroFit = retroFit;
    }

    @Override public Either<String, Pokemon> getPokemonsById(Integer pokedexNum) {
        Either<String, Pokemon> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        PokemonApi api = retrofit.create(PokemonApi.class);

        try {
            Response<Pokemon> pokemonResponse = api.getPokemon(pokedexNum).execute();
            if (pokemonResponse.isSuccessful() && pokemonResponse.body() != null) {
                resultado = Either.right(pokemonResponse.body());
            } else {
                assert pokemonResponse.errorBody() != null;
                resultado = Either.left(pokemonResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }

        return resultado;
    }

    @Override public Either<String, List<Result>> getAllPokemonsIds(){
        Either<String, List<Result>> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        PokemonApi api = retrofit.create(PokemonApi.class);
        List<Result> listaModificada;
        try {
            Response<PokemonResponse> pokemonResponse = api.getAllPokemonsIds(10000).execute();

            if (pokemonResponse.isSuccessful() && pokemonResponse.body() != null) {
                listaModificada = pokemonResponse.body().getResults();

                resultado = Either.right(listaModificada.stream().map(result ->{
                            String modifiedUrl = Arrays.stream(result.getUrl().split("/")).reduce((s, s2) -> s2).get();// Modify the URL
                            return new Result(result.getName(), modifiedUrl);
                        }
                ).toList());
            } else {
                assert pokemonResponse.errorBody() != null;
                resultado = Either.left(pokemonResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }

        return resultado;

    }
}
