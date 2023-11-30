package server.domain.usecases.albums;

import domain.model.Album;
import jakarta.inject.Inject;
import server.dao.DaoAlbums;

import java.util.List;

public class GetAllAlbumsUseCase {
    private final DaoAlbums daoAlbums;

    @Inject
    public GetAllAlbumsUseCase(DaoAlbums daoAlbums) {
        this.daoAlbums = daoAlbums;
    }

    public List<Album> getAllAlbums() {
        return daoAlbums.getAllAlbums();
    }
}