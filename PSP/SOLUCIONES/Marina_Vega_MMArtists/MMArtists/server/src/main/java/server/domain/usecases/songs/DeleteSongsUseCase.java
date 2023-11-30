package server.domain.usecases.songs;

import domain.model.Song;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import server.dao.DaoSongs;
import server.domain.common.DomainConstants;
import server.domain.errors.ObjectNotFoundException;

import java.util.List;

@Log4j2
public class DeleteSongsUseCase {
    private final DaoSongs daoSongsImpl;

    @Inject
    public DeleteSongsUseCase(DaoSongs daoSongsImpl) {
        this.daoSongsImpl = daoSongsImpl;
    }

    public boolean deleteSongs(List<Song> songsToDelete) {
        if (!daoSongsImpl.deleteSongs(songsToDelete)) {
            log.error(DomainConstants.THE_SONG_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
            throw new ObjectNotFoundException(DomainConstants.THE_SONG_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
        } else {
            return true;
        }
    }
}