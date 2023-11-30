package org.example.dao;

import lombok.extern.log4j.Log4j2;
import org.example.dao.common.DaoConstants;
import org.example.domain.model.Song;
import org.example.domain.model.exceptions.ObjectNotFoundException;

import java.util.List;

@Log4j2
public class DaoSongs {

    public List<Song> getAllSongs() {
        return StaticLists.songList;
    }

    public Song getSong(int id) {
        Song song = StaticLists.songList.stream()
                .filter(song1 -> song1.getId() == id)
                .findFirst()
                .orElse(null);
        if (song == null) {
            log.error(DaoConstants.THE_SONG_IS_NOT_IN_THE_LIST);
            throw new ObjectNotFoundException(DaoConstants.THE_SONG_IS_NOT_IN_THE_LIST);
        }
        return song;
    }

    public boolean addSong(Song song) {
        Song lastSongOfTheList = StaticLists.songList.get(StaticLists.songList.size() - 1);
        int id = lastSongOfTheList.getId() + 1;
        song.setId(id);
        return StaticLists.songList.add(song);
    }

    public Song updateSong(Song song) {
        int indexOfSong = StaticLists.songList.indexOf(song);
        if (indexOfSong >= 0) {
            StaticLists.songList.set(indexOfSong, song);
        } else {
            log.error(DaoConstants.THE_SONG_IS_NOT_IN_THE_LIST);
            throw new ObjectNotFoundException(DaoConstants.THE_SONG_IS_NOT_IN_THE_LIST);
        }
        return song;
    }

    public boolean deleteSong(Song songToDelete) {
        return StaticLists.songList.remove(songToDelete);
    }
}