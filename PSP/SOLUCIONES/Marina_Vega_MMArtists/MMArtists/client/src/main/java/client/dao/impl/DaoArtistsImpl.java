package client.dao.impl;

import client.dao.DaoArtists;
import client.dao.retrofit.llamadas.ArtistApi;
import com.google.gson.Gson;
import domain.common.ApiError;
import domain.model.Artist;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class DaoArtistsImpl extends DaoGenerics implements DaoArtists {

    private final ArtistApi artistApi;

    @Inject
    public DaoArtistsImpl(ArtistApi artistApi, Gson gson) {
        super(gson);
        this.artistApi = artistApi;
    }

    @Override
    public Single<Either<ApiError, List<Artist>>> getAllArtists() {
        return safeSingleApiCall(artistApi.getAllArtists());
    }

    @Override
    public Single<Either<ApiError, Artist>> addArtist(Artist artist) {
        return safeSingleApiCall(artistApi.addArtist(artist));
    }

    @Override
    public Single<Either<ApiError, Artist>> updateArtist(Artist artist) {
        return safeSingleApiCall(artistApi.updateArtist(artist));
    }

    @Override
    public Single<Either<ApiError, String>> deleteArtist(String artistId) {
        return safeSingleVoidApiCall(artistApi.deleteArtist(artistId));
    }
}