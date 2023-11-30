package client.domain.usecases.albums;

import client.dao.DaoAlbums;
import domain.common.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DeleteAlbumUseCase {

    private final DaoAlbums daoAlbums;

    @Inject
    public DeleteAlbumUseCase(DaoAlbums daoAlbums) {
        this.daoAlbums = daoAlbums;
    }

    public Single<Either<ApiError, String>> deleteAlbum(String albumId) {
        return daoAlbums.deleteAlbum(albumId);
    }
}