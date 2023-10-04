package domain.retrofit;



import domain.modelo.pokemon.Pokemon;
import domain.modelo.pokemon.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface PokemonApi {

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int numPokedex);

    @GET("pokemon")
    Call<PokemonResponse> getAllPokemonsIds(@Query("limit") int numberLimit);

}


