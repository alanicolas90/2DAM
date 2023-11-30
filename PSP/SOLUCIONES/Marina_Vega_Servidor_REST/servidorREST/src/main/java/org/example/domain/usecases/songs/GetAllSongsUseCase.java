package org.example.domain.usecases.songs;

import jakarta.inject.Inject;
import org.example.dao.DaoSongs;
import org.example.domain.model.Song;

import java.util.List;

public class GetAllSongsUseCase {
    private final DaoSongs daoSongs;

    @Inject
    public GetAllSongsUseCase(DaoSongs daoSongs) {
        this.daoSongs = daoSongs;
    }

    public List<Song> getAllSongs() {
        return daoSongs.getAllSongs();
    }
}