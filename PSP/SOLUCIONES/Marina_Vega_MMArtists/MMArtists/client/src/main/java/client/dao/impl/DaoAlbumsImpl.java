package client.dao.impl;


import client.dao.DaoAlbums;
import client.dao.retrofit.llamadas.AlbumApi;
import com.google.gson.Gson;
import domain.common.ApiError;
import domain.model.Album;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class DaoAlbumsImpl extends DaoGenerics implements DaoAlbums {
    private final AlbumApi albumApi;

    @Inject
    public DaoAlbumsImpl(AlbumApi albumApi, Gson gson) {
        super(gson);
        this.albumApi = albumApi;
    }

    @Override
    public Single<Either<ApiError, List<Album>>> getAllAlbums() {
        return safeSingleApiCall(albumApi.getAllAlbums());
    }

    @Override
    public Single<Either<ApiError, List<Album>>> getAlbumsByArtist(String artistId) {
        return safeSingleApiCall(albumApi.getAlbumsByArtist(artistId));
    }

    @Override
    public Single<Either<ApiError, String>> deleteAlbum(String albumId) {
        return safeSingleVoidApiCall(albumApi.deleteAlbum(albumId));
    }
}