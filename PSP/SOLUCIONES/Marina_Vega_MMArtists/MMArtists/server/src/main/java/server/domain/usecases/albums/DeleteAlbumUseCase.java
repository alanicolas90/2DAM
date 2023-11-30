package server.domain.usecases.albums;

import domain.model.Album;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import server.dao.DaoAlbums;
import server.domain.common.DomainConstants;
import server.domain.errors.ObjectNotFoundException;

@Log4j2
public class DeleteAlbumUseCase {
    private final DaoAlbums daoAlbums;

    @Inject
    public DeleteAlbumUseCase(DaoAlbums daoAlbums) {
        this.daoAlbums = daoAlbums;
    }

    public boolean deleteAlbum(String id) {
        Album albumToDelete = new Album(id);
        if (!daoAlbums.deleteAlbum(albumToDelete)) {
            log.error(DomainConstants.THE_ALBUM_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
            throw new ObjectNotFoundException(DomainConstants.THE_ALBUM_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
        } else {
            return true;
        }
    }
}