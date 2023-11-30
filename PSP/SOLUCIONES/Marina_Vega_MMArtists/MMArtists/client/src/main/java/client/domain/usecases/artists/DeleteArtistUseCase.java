package client.domain.usecases.artists;

import client.dao.DaoArtists;
import domain.common.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DeleteArtistUseCase {

    private final DaoArtists daoArtists;

    @Inject
    public DeleteArtistUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }

    public Single<Either<ApiError, String>> deleteArtist(String artistId) {
        return daoArtists.deleteArtist(artistId);
    }
}