package server.domain.usecases.songs;

import domain.model.Song;
import jakarta.inject.Inject;
import server.dao.DaoSongs;

import java.util.List;

public class GetSongsByAlbumUseCase {
    private final DaoSongs daoSongsImpl;

    @Inject
    public GetSongsByAlbumUseCase(DaoSongs daoSongsImpl) {
        this.daoSongsImpl = daoSongsImpl;
    }

    public List<Song> getSongsByAlbum(String artistId) {
        return daoSongsImpl.getSongsByAlbum(artistId);
    }

}