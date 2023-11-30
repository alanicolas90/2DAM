package server.domain.usecases.songs;

import domain.model.Song;
import jakarta.inject.Inject;
import server.dao.DaoSongs;

import java.util.List;

public class GetAllSongsUseCase {
    private final DaoSongs daoSongsImpl;

    @Inject
    public GetAllSongsUseCase(DaoSongs daoSongsImpl) {
        this.daoSongsImpl = daoSongsImpl;
    }

    public List<Song> getAllSongs() {
        return daoSongsImpl.getAllSongs();
    }
}