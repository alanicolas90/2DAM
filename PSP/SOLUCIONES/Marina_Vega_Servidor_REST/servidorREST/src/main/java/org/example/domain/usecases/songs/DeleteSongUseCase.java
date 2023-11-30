package org.example.domain.usecases.songs;

import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.example.dao.DaoSongs;
import org.example.dao.common.DaoConstants;
import org.example.domain.model.Song;
import org.example.domain.model.exceptions.ObjectNotFoundException;

@Log4j2
public class DeleteSongUseCase {
    private final DaoSongs daoSongs;

    @Inject
    public DeleteSongUseCase(DaoSongs daoSongs) {
        this.daoSongs = daoSongs;
    }

    public boolean deleteSong(int id) {
        Song artistToDelete = new Song(id);
        if (!daoSongs.deleteSong(artistToDelete)) {
            log.error(DaoConstants.THE_SONG_IS_NOT_IN_THE_LIST);
            throw new ObjectNotFoundException(DaoConstants.THE_SONG_IS_NOT_IN_THE_LIST);
        } else {
            return true;
        }
    }
}