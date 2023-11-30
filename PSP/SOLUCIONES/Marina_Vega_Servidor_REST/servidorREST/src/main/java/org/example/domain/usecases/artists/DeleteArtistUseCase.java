package org.example.domain.usecases.artists;

import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.example.dao.DaoArtists;
import org.example.dao.common.DaoConstants;
import org.example.domain.model.Artist;
import org.example.domain.model.exceptions.ObjectNotFoundException;

@Log4j2
public class DeleteArtistUseCase {
    private final DaoArtists daoArtists;

    @Inject
    public DeleteArtistUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }

    public boolean deleteArtist(int id) {
        Artist artistToDelete = new Artist(id);
        if (!daoArtists.deleteArtist(artistToDelete)) {
            log.error(DaoConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
            throw new ObjectNotFoundException(DaoConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
        } else {
            return true;
        }
    }
}