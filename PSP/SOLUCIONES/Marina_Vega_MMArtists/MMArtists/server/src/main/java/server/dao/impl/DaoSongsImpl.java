package server.dao.impl;

import domain.model.Song;
import server.dao.DaoSongs;
import server.dao.data.StaticLists;

import java.util.List;

public class DaoSongsImpl implements DaoSongs {

    @Override
    public List<Song> getAllSongs() {
        return StaticLists.songList;
    }

    @Override
    public List<Song> getSongsByAlbum(String albumId) {
        return StaticLists.songList.stream().filter(song -> song.getAlbumsThatContainThisSong().contains(albumId)).toList();
    }

    @Override
    public boolean deleteSongs(List<Song> songsToDelete) {
        return StaticLists.songList.removeAll(songsToDelete);
    }
}