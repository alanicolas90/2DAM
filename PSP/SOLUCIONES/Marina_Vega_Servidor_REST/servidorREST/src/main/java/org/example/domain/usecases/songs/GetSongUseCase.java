package org.example.domain.usecases.songs;

import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.example.dao.DaoSongs;
import org.example.domain.model.Song;

@Log4j2
public class GetSongUseCase {
    private final DaoSongs daoSongs;

    @Inject
    public GetSongUseCase(DaoSongs daoSongs) {
        this.daoSongs = daoSongs;
    }

    public Song getSong(int id) {
        return daoSongs.getSong(id);
    }
}