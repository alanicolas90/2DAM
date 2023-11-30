package server.domain.usecases.artists;

import domain.model.Artist;
import jakarta.inject.Inject;
import server.dao.DaoArtists;

import java.util.List;

public class GetAllArtistsUseCase {
    private final DaoArtists daoArtists;

    @Inject
    public GetAllArtistsUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }

    public List<Artist> getAllArtists() {
        return daoArtists.getAllArtists();
    }
}