package client.dao;

import domain.common.ApiError;
import domain.model.Artist;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface DaoArtists {
    Single<Either<ApiError, List<Artist>>> getAllArtists();

    Single<Either<ApiError, Artist>> addArtist(Artist artist);

    Single<Either<ApiError, Artist>> updateArtist(Artist artist);

    Single<Either<ApiError, String>> deleteArtist(String artistId);
}