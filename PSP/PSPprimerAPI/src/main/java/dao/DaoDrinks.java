package dao;

import domain.modelo.drinks.alcohol.AlcoholicIdResponse;
import domain.modelo.drinks.alcohol.AlcoholicResponse;
import domain.modelo.drinks.category.CategoriesResponse;
import domain.modelo.drinks.drink.Drink;
import domain.modelo.drinks.ingrediente.DrinksIngredientsResponse;
import domain.modelo.drinks.drink.DrinksResponse;
import domain.modelo.drinks.glass.GlassesResponse;
import domain.modelo.drinks.ingrediente.IngredientSpecificResponse;
import domain.modelo.drinks.ingrediente.IngredienteResponse;
import domain.retrofit.DrinksApi;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;


@Log4j2
public class DaoDrinks {
    private final RetroFit retroFit;

    @Inject
    public DaoDrinks(RetroFit retroFit) {
        this.retroFit = retroFit;
    }

    public Either<String, DrinksResponse> getRandomDrink() {
        Either<String, DrinksResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<DrinksResponse> drinksResponse = api.getRandomDrink().execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, DrinksResponse> getDrinkByName(String nameDrink) {
        Either<String, DrinksResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<DrinksResponse> drinksResponse = api.getDrinkByName(nameDrink).execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, DrinksResponse> getDrinkByFirstLetter(String string) {
        Either<String, DrinksResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<DrinksResponse> drinksResponse = api.getDrinkByFirstLetter(string).execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, DrinksIngredientsResponse> getDrinkByIngredient(String string) {
        Either<String, DrinksIngredientsResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<DrinksIngredientsResponse> drinksResponse = api.getDrinkByIngredient(string).execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, AlcoholicIdResponse> getAlcoholicDrinks() {
        Either<String, AlcoholicIdResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<AlcoholicIdResponse> drinksResponse = api.getAlcoholicOrNonAlcoholicDrinks("Alcoholic").execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, AlcoholicIdResponse> getNonAlcoholicDrinks() {
        Either<String, AlcoholicIdResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<AlcoholicIdResponse> drinksResponse = api.getAlcoholicOrNonAlcoholicDrinks("Non_Alcoholic").execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, CategoriesResponse> getCategories() {
        Either<String, CategoriesResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<CategoriesResponse> drinksResponse = api.getCategories().execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, DrinksResponse> getDrinkById( int id) {
        Either<String, DrinksResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<DrinksResponse> drinksResponse = api.getDrinkById(id).execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, IngredientSpecificResponse> getDrinkByIdIngredient(int id) {
        Either<String, IngredientSpecificResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<IngredientSpecificResponse> drinksResponse = api.getDrinkByIdIngredient(id).execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }


    public Either<String, GlassesResponse> getGlasses() {
        Either<String, GlassesResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<GlassesResponse> drinksResponse = api.getGlasses().execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, IngredienteResponse> getIngredients() {
        Either<String, IngredienteResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<IngredienteResponse> drinksResponse = api.getIngredients().execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }

    public Either<String, AlcoholicResponse> getAlcoholicFilter() {
        Either<String, AlcoholicResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        DrinksApi api = retrofit.create(DrinksApi.class);

        try {
            Response<AlcoholicResponse> drinksResponse = api.getAlcoholicFilters().execute();
            if (drinksResponse.isSuccessful() && drinksResponse.body() != null) {
                resultado = Either.right(drinksResponse.body());
            } else {
                resultado = Either.left(drinksResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }
        return resultado;
    }



}
