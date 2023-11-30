package server.dao;

import domain.model.Album;

import java.util.List;

public interface DaoAlbums {
    List<Album> getAllAlbums();

    List<Album> getAlbumsByArtist(String artistId);

    Album getAlbum(String albumId);

    boolean deleteAlbum(Album albumToDelete);
}