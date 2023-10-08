package dao.impl;

import dao.DaoBerry;
import dao.RetroFit;
import domain.modelo.berry.BerriesResponse;
import domain.modelo.berry.BerryResponse;
import domain.modelo.berry.ResultsItem;
import domain.retrofit.BerryApi;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class DaoBerryImpl implements DaoBerry {


    private final RetroFit retroFit;

    @Inject
    public DaoBerryImpl(RetroFit retroFit) {
        this.retroFit = retroFit;
    }

    @Override
    public Either<String, List<ResultsItem>> getAllBerriesWithIdMod() {
        Either<String, List<ResultsItem>> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        BerryApi api = retrofit.create(BerryApi.class);
        List<ResultsItem> listaModificada;

        try {
            Response<BerriesResponse> berryResponse = api.getAllBerriesIds(100).execute();

            if (berryResponse.isSuccessful() && berryResponse.body() != null) {
                listaModificada = berryResponse.body().getResults();

                resultado = Either.right(listaModificada.stream().map(result ->{
                            String modifiedUrl = Arrays.stream(result.getUrl().split("/")).reduce((s, s2) -> s2).get();// Modify the URL
                            return new ResultsItem(result.getName(), modifiedUrl);
                        }).toList());
            }else{
                assert berryResponse.errorBody() != null;
                resultado = Either.left(berryResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }

        return resultado;
    }

    @Override
    public Either<String, BerriesResponse> getAllBerriesNormal() {
        Either<String, BerriesResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        BerryApi api = retrofit.create(BerryApi.class);

        try {
            Response<BerriesResponse> berryResponse = api.getAllBerriesIds(100).execute();

            if (berryResponse.isSuccessful() && berryResponse.body() != null) {
                resultado = Either.right(berryResponse.body());
            }else{
                assert berryResponse.errorBody() != null;
                resultado = Either.left(berryResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }

        return resultado;

    }

    @Override
    public Either<String , BerryResponse> getSpecificBerry(Integer idBerry){
        Either<String, BerryResponse> resultado;
        Retrofit retrofit = retroFit.getRetrofit();
        BerryApi api = retrofit.create(BerryApi.class);

        try {
            Response<BerryResponse> berryResponse = api.getSpecificBerry(idBerry).execute();

            if (berryResponse.isSuccessful() && berryResponse.body() != null) {
                resultado = Either.right(berryResponse.body());
            }else{
                assert berryResponse.errorBody() != null;
                resultado = Either.left(berryResponse.errorBody().toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resultado = Either.left(e.getMessage());
        }

        return resultado;
    }
}
