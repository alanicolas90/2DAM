package server.domain.usecases.artists;

import domain.model.Artist;
import jakarta.inject.Inject;
import server.dao.DaoArtists;
import server.domain.common.DomainConstants;
import server.domain.errors.NotValidFieldException;
import server.domain.errors.NullObjectException;

import java.time.LocalDateTime;

public class AddArtistUseCase {
    private final DaoArtists daoArtists;

    @Inject
    public AddArtistUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }

    public boolean addArtist(Artist artist) {
        boolean isAdded;
        if (artist != null) {
            nameIsValid(artist);
            countryIsValid(artist);
            debutYearIsValid(artist);
            isAdded = daoArtists.addArtist(artist);
        } else {
            throw new NullObjectException(DomainConstants.THE_ARTIST_IS_NULL);
        }
        return isAdded;
    }

    private void nameIsValid(Artist artist) {
        if (artist.getName() == null || artist.getName().isEmpty()) {
            throw new NotValidFieldException(domain.common.DomainConstants.THE_NAME_OF_THE_ARTIST_IS_NOT_VALID);
        }
    }

    private void countryIsValid(Artist artist) {
        if (artist.getCountry() == null || artist.getCountry().isEmpty()) {
            throw new NotValidFieldException(domain.common.DomainConstants.THE_COUNTRY_OF_THE_ARTIST_IS_NOT_VALID);
        }
    }

    private void debutYearIsValid(Artist artist) {
        if (artist.getDebutYear() < 0 || artist.getDebutYear() >= LocalDateTime.now().getYear()) {
            throw new NotValidFieldException(domain.common.DomainConstants.THE_DEBUT_YEAR_OF_THE_ARTIST_IS_NOT_VALID);
        }
    }
}