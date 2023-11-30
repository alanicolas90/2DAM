package server.dao;

import domain.model.Song;

import java.util.List;

public interface DaoSongs {
    List<Song> getAllSongs();

    List<Song> getSongsByAlbum(String albumId);

    boolean deleteSongs(List<Song> songsToDelete);
}