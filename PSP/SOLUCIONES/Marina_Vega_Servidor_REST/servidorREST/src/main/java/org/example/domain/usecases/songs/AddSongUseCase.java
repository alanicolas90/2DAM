package org.example.domain.usecases.songs;

import jakarta.inject.Inject;
import org.example.dao.DaoArtists;
import org.example.dao.DaoSongs;
import org.example.domain.common.DomainConstants;
import org.example.domain.model.Artist;
import org.example.domain.model.Song;
import org.example.domain.model.exceptions.NotValidFieldException;
import org.example.domain.model.exceptions.NullObjectException;

public class AddSongUseCase {
    private final DaoSongs daoSongs;
    private final DaoArtists daoArtists;

    @Inject
    public AddSongUseCase(DaoSongs daoSongs, DaoArtists daoArtists) {
        this.daoSongs = daoSongs;
        this.daoArtists = daoArtists;
    }

    public boolean addSong(Song song) {
        boolean isAdded;
        if (song != null) {
            nameIsValid(song);
            lengthIsValid(song);
            artistIsValid(song.getArtist());
            isAdded = daoSongs.addSong(song);
        } else {
            throw new NullObjectException(DomainConstants.THE_SONG_IS_NULL);
        }
        return isAdded;
    }

    private void nameIsValid(Song song) {
        if (song.getName() == null || song.getName().isEmpty()) {
            throw new NotValidFieldException(DomainConstants.THE_NAME_OF_THE_SONG_IS_NOT_VALID);
        }
    }

    private void lengthIsValid(Song song) {
        if (song.getLength() < 0) {
            throw new NotValidFieldException(DomainConstants.THE_LENGTH_OF_THE_SONG_IS_NOT_VALID);
        }
    }

    private void artistIsValid(Artist artist) {
        if (daoArtists.getArtist(artist.getId()) == null) {
            throw new NotValidFieldException(DomainConstants.THE_ARTIST_IS_NOT_VALID);
        }
    }
}