package client.domain.usecases.albums;

import client.dao.DaoAlbums;
import domain.common.ApiError;
import domain.model.Album;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAlbumsByArtistUseCase {
    private final DaoAlbums daoAlbums;

    @Inject
    public GetAlbumsByArtistUseCase(DaoAlbums daoAlbums) {
        this.daoAlbums = daoAlbums;
    }

    public Single<Either<ApiError, List<Album>>> getAlbumsByArtist(String artistId) {
        return daoAlbums.getAlbumsByArtist(artistId);
    }
}