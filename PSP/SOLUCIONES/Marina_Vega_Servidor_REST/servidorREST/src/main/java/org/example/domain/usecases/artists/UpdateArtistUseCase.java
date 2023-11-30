package org.example.domain.usecases.artists;

import jakarta.inject.Inject;
import org.example.dao.DaoArtists;
import org.example.domain.common.DomainConstants;
import org.example.domain.model.Artist;
import org.example.domain.model.exceptions.NotValidFieldException;
import org.example.domain.model.exceptions.NullObjectException;

import java.time.LocalDateTime;

public class UpdateArtistUseCase {
    private final DaoArtists daoArtists;

    @Inject
    public UpdateArtistUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }

    public Artist updateArtist(Artist artist) {
        Artist artistToUpdate;
        if (artist != null) {
            nameIsValid(artist);
            countryIsValid(artist);
            debutYearIsValid(artist);
            artistToUpdate = daoArtists.updateArtist(artist);
        } else {
            throw new NullObjectException(DomainConstants.THE_ARTIST_IS_NULL);
        }
        return artistToUpdate;
    }

    private void nameIsValid(Artist artist) {
        if (artist.getName() == null || artist.getName().isEmpty()) {
            throw new NotValidFieldException(DomainConstants.THE_NAME_OF_THE_ARTIST_IS_NOT_VALID);
        }
    }

    private void countryIsValid(Artist artist) {
        if (artist.getCountry() == null || artist.getCountry().isEmpty()) {
            throw new NotValidFieldException(DomainConstants.THE_COUNTRY_OF_THE_ARTIST_IS_NOT_VALID);
        }
    }

    private void debutYearIsValid(Artist artist) {
        if (artist.getDebutYear() < 0 || artist.getDebutYear() >= LocalDateTime.now().getYear()) {
            throw new NotValidFieldException(DomainConstants.THE_DEBUT_YEAR_OF_THE_ARTIST_IS_NOT_VALID);
        }
    }
}