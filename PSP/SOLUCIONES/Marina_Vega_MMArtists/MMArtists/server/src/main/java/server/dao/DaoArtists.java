package server.dao;

import domain.model.Artist;

import java.util.List;

public interface DaoArtists {
    List<Artist> getAllArtists();

    boolean addArtist(Artist artist);

    Artist updateArtist(Artist artist, int indexOfArtist);

    boolean deleteArtist(Artist artistToDelete);
}