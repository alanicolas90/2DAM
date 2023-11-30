package org.example.dao;

import lombok.extern.log4j.Log4j2;
import org.example.dao.common.DaoConstants;
import org.example.domain.model.Artist;
import org.example.domain.model.exceptions.ObjectNotFoundException;

import java.util.List;

@Log4j2
public class DaoArtists {

    public List<Artist> getAllArtists() {
        return StaticLists.artistList;
    }

    public Artist getArtist(int id) {
        Artist artist = StaticLists.artistList.stream()
                .filter(artist1 -> artist1.getId() == id)
                .findFirst()
                .orElse(null);
        if (artist == null) {
            log.error(DaoConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
            throw new ObjectNotFoundException(DaoConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
        }
        return artist;
    }

    public boolean addArtist(Artist artist) {
        Artist lastArtistOfTheList = StaticLists.artistList.get(StaticLists.artistList.size() - 1);
        int id = lastArtistOfTheList.getId() + 1;
        artist.setId(id);
        return StaticLists.artistList.add(artist);
    }

    public Artist updateArtist(Artist artist) {
        int indexOfArtist = StaticLists.artistList.indexOf(artist);
        if (indexOfArtist >= 0) {
            StaticLists.artistList.set(indexOfArtist, artist);
        } else {
            log.error(DaoConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
            throw new ObjectNotFoundException(DaoConstants.THE_ARTIST_IS_NOT_IN_THE_LIST_CHECK_THE_ID);
        }
        return artist;
    }

    public boolean deleteArtist(Artist artistToDelete) {
        boolean isDeleted = false;
        if (StaticLists.artistList.remove(artistToDelete)) {
            StaticLists.songList.removeAll(StaticLists.songList.stream().filter(song -> song.getArtist().equals(artistToDelete)).toList());
            isDeleted = true;
        }
        return isDeleted;
    }
}