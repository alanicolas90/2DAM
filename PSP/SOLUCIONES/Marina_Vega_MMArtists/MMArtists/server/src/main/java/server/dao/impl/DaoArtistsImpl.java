package server.dao.impl;

import domain.model.Album;
import domain.model.Artist;
import server.dao.DaoArtists;
import server.dao.data.StaticLists;

import java.util.HashSet;
import java.util.List;

public class DaoArtistsImpl implements DaoArtists {

    @Override
    public List<Artist> getAllArtists() {
        return StaticLists.artistList;
    }

    @Override
    public boolean addArtist(Artist artist) {
        Artist lastArtistOfTheList = StaticLists.artistList.get(StaticLists.artistList.size() - 1);
        int id = Integer.parseInt(lastArtistOfTheList.getId()) + 1;
        artist.setId(Integer.toString(id));
        return StaticLists.artistList.add(artist);
    }

    @Override
    public Artist updateArtist(Artist artist, int indexOfArtist) {
        StaticLists.artistList.set(indexOfArtist, artist);
        return artist;
    }

    @Override
    public boolean deleteArtist(Artist artistToDelete) {
        boolean isDeleted = false;
        if (StaticLists.artistList.remove(artistToDelete)) {
            List<Album> albumsToFilter = StaticLists.albumList.stream().filter(album -> album.getArtistId().equals(artistToDelete.getId())).toList();
            List<String> idOfAlbumsToFilter = albumsToFilter.stream().map(Album::getId).toList();
            StaticLists.songList.removeAll(StaticLists.songList.stream()
                    .filter(song -> new HashSet<>(idOfAlbumsToFilter)
                            .containsAll(song.getAlbumsThatContainThisSong()))
                    .toList());
            StaticLists.albumList.removeAll(albumsToFilter);
            isDeleted = true;
        }
        return isDeleted;
    }
}