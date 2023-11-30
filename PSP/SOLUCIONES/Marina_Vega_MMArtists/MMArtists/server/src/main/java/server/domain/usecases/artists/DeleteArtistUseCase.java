package server.domain.usecases.artists;

import domain.model.Artist;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import server.dao.DaoArtists;
import server.domain.common.DomainConstants;
import server.domain.errors.ObjectNotFoundException;

@Log4j2
public class DeleteArtistUseCase {
    private final DaoArtists daoArtists;

    @Inject
    public DeleteArtistUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }

    public boolean deleteArtist(String id) {
        Artist artistToDelete = new Artist(id);
        if (!daoArtists.deleteArtist(artistToDelete)) {
            log.error(DomainConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
            throw new ObjectNotFoundException(DomainConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
        } else {
            return true;
        }
    }
}