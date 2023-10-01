package domain.retrofit;


import domain.modelo.pokemon.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface PokemonApi {

    @GET("pokemon/{id}")
    Call<PokemonResponse> getPokemon(@Path("id") int numPokedex);

}


