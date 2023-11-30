package client.domain.usecases.albums;

import client.dao.DaoAlbums;
import domain.common.ApiError;
import domain.model.Album;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllAlbumsUseCase {
    private final DaoAlbums daoAlbums;

    @Inject
    public GetAllAlbumsUseCase(DaoAlbums daoAlbums) {
        this.daoAlbums = daoAlbums;
    }

    public Single<Either<ApiError, List<Album>>> getAllAlbums() {
        return daoAlbums.getAllAlbums();
    }
}