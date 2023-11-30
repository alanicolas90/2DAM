package server.domain.usecases.albums;

import domain.model.Album;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import server.dao.DaoAlbums;
import server.domain.common.DomainConstants;
import server.domain.errors.ObjectNotFoundException;

@Log4j2
public class GetAlbumUseCase {
    private final DaoAlbums daoAlbums;

    @Inject
    public GetAlbumUseCase(DaoAlbums daoAlbums) {
        this.daoAlbums = daoAlbums;
    }

    public Album getAlbum(String albumId) {
        Album album = daoAlbums.getAlbum(albumId);
        if (album == null) {
            log.error(DomainConstants.THE_ALBUM_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
            throw new ObjectNotFoundException(DomainConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
        }
        return album;
    }
}