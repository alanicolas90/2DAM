package client.domain.usecases.artists;

import client.dao.DaoArtists;
import domain.common.ApiError;
import domain.model.Artist;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllArtistsUseCase {

    private final DaoArtists daoArtists;

    @Inject
    public GetAllArtistsUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }

    public Single<Either<ApiError, List<Artist>>> getAllArtists() {
        return daoArtists.getAllArtists();
    }
}