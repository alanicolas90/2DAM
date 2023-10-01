package dao;

import domain.modelo.pokemon.PokemonResponse;
import domain.retrofit.PokemonApi;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Log4j2
public class DaoPokemon {

    private final RetroFit retroFit;

    @Inject
    public DaoPokemon(RetroFit retroFit) {
        this.retroFit = retroFit;
    }

    public Either<String, PokemonResponse> getPokemonsById(Integer pokedexNum) {
        Either<String, PokemonResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        PokemonApi api = retrofit.create(PokemonApi.class);

        try {
            Response<PokemonResponse> pokemonResponse = api.getPokemon(pokedexNum).execute();
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
}
