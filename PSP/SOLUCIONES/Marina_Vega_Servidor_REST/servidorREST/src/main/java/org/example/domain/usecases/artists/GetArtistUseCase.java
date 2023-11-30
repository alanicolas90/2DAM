package org.example.domain.usecases.artists;

import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.example.dao.DaoArtists;
import org.example.domain.model.Artist;

@Log4j2
public class GetArtistUseCase {
    private final DaoArtists daoArtists;

    @Inject
    public GetArtistUseCase(DaoArtists daoArtists) {
        this.daoArtists = daoArtists;
    }

    public Artist getArtist(int id) {
        return daoArtists.getArtist(id);
    }
}