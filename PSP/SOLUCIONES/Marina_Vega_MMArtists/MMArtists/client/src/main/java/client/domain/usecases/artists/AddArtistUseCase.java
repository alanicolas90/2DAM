package client.domain.usecases.artists;

import client.dao.DaoArtists;
import domain.common.ApiError;
import domain.common.DomainConstants;
import domain.model.Artist;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

public class AddArtistUseCase {

    private final DaoArtists daoArtists;

    @Inject
    public AddArtistUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }


    public Single<Either<ApiError, Artist>> addArtist(Artist artist) {
        return nameIsValid(artist)
                .flatMap(o -> countryIsValid(artist))
                .flatMap(o -> debutYearIsValid(artist))
                .flatMap(o -> daoArtists.addArtist(artist));
    }

    private Single<Either<ApiError, Object>> nameIsValid(Artist artist) {
        if (artist.getName() == null || artist.getName().isEmpty()) {
            return Single.just(Either.left(new ApiError(DomainConstants.THE_NAME_OF_THE_ARTIST_IS_NOT_VALID)));
        }
        return Single.just(Either.right(null));

    }

    private Single<Either<ApiError, Object>> countryIsValid(Artist artist) {
        if (artist.getCountry() == null || artist.getCountry().isEmpty()) {
            return Single.just(Either.left(new ApiError(DomainConstants.THE_COUNTRY_OF_THE_ARTIST_IS_NOT_VALID)));
        }
        return Single.just(Either.right(null));

    }

    private Single<Either<ApiError, Object>> debutYearIsValid(Artist artist) {
        if (artist.getDebutYear() < 0 || artist.getDebutYear() >= LocalDateTime.now().getYear()) {
            return Single.just(Either.left(new ApiError(DomainConstants.THE_DEBUT_YEAR_OF_THE_ARTIST_IS_NOT_VALID)));
        }
        return Single.just(Either.right(null));
    }
}