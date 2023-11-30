package server.domain.usecases.artists;

import domain.model.Artist;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import server.dao.DaoArtists;
import server.domain.common.DomainConstants;
import server.domain.errors.NotValidFieldException;
import server.domain.errors.NullObjectException;
import server.domain.errors.ObjectNotFoundException;

import java.time.LocalDateTime;

@Log4j2
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
            int indexOfArtist = daoArtists.getAllArtists().indexOf(artist);
            if (indexOfArtist >= 0) {
                artistToUpdate = daoArtists.updateArtist(artist, indexOfArtist);
            } else {
                log.error(DomainConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
                throw new ObjectNotFoundException(DomainConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
            }
        } else {
            throw new NullObjectException(DomainConstants.THE_ARTIST_IS_NULL);
        }
        return artistToUpdate;
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