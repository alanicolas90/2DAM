package org.example.domain.usecases.artists;

import jakarta.inject.Inject;
import org.example.dao.DaoArtists;
import org.example.domain.model.Artist;

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