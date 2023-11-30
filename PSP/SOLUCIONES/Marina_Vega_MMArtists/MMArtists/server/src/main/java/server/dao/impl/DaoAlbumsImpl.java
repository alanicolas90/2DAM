package server.dao.impl;

import domain.model.Album;
import domain.model.Song;
import lombok.extern.log4j.Log4j2;
import server.dao.DaoAlbums;
import server.dao.data.StaticLists;

import java.util.List;

@Log4j2
public class DaoAlbumsImpl implements DaoAlbums {
    @Override
    public List<Album> getAllAlbums() {
        return StaticLists.albumList;
    }

    @Override
    public List<Album> getAlbumsByArtist(String artistId) {
        return StaticLists.albumList.stream().filter(album -> album.getArtistId().equals(artistId)).toList();
    }

    @Override
    public Album getAlbum(String albumId) {
        return StaticLists.albumList.stream()
                .filter(album1 -> album1.getId().equals(albumId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteAlbum(Album albumToDelete) {
        boolean isDeleted = false;
        if (StaticLists.albumList.remove(albumToDelete)) {
            List<Song> songsThatContainOnlyTheAlbumToDelete = StaticLists.songList.stream().filter(song -> song.getAlbumsThatContainThisSong().stream().allMatch(albumId -> albumId.equals(albumToDelete.getId()))).toList();
            StaticLists.songList.removeAll(songsThatContainOnlyTheAlbumToDelete);
            StaticLists.songList.forEach(song -> {
                song.getAlbumsThatContainThisSong().remove(albumToDelete.getId());
                if (song.getAlbumsThatContainThisSong().isEmpty()) {
                    StaticLists.songList.remove(song);
                }
            });
            isDeleted = true;
        }
        return isDeleted;
    }
}