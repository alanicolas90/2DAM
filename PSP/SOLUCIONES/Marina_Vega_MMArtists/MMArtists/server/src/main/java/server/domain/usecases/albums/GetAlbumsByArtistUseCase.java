package server.domain.usecases.albums;

import domain.model.Album;
import jakarta.inject.Inject;
import server.dao.DaoAlbums;

import java.util.List;

public class GetAlbumsByArtistUseCase {
    private final DaoAlbums daoAlbums;

    @Inject
    public GetAlbumsByArtistUseCase(DaoAlbums daoAlbums) {
        this.daoAlbums = daoAlbums;
    }

    public List<Album> getAlbumByArtist(String artistId) {
        return daoAlbums.getAlbumsByArtist(artistId);
    }
}