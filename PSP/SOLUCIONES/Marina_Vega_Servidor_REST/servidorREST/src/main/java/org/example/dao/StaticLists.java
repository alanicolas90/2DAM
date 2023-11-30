package org.example.dao;

import org.example.dao.common.DaoConstants;
import org.example.domain.model.Artist;
import org.example.domain.model.Song;

import java.util.ArrayList;
import java.util.List;

public class StaticLists {

    protected static final List<Artist> artistList = new ArrayList<>(List.of(
            new Artist(1, DaoConstants.BAD_OMENS, DaoConstants.USA, 2015),
            new Artist(2, DaoConstants.BRING_ME_THE_HORIZON, DaoConstants.UK, 2004),
            new Artist(3, DaoConstants.MOTIONLESS_IN_WHITE, DaoConstants.USA, 2005),
            new Artist(4, DaoConstants.BLACK_VEIL_BRIDES, DaoConstants.USA, 2006),
            new Artist(5, DaoConstants.SET_IT_OFF, DaoConstants.USA, 2008),
            new Artist(6, DaoConstants.LINKIN_PARK, DaoConstants.USA, 1996)
    ));
    protected static final List<Song> songList = new ArrayList<>(List.of(
            new Song(1, DaoConstants.LIKE_A_VILLAIN, 210, artistList.get(0)),
            new Song(2, DaoConstants.THRONE, 191, artistList.get(1)),
            new Song(3, DaoConstants.SOFT, 210, artistList.get(2)),
            new Song(4, DaoConstants.FALLEN_ANGELS, 224, artistList.get(3)),
            new Song(5, DaoConstants.WHY_WORRY, 199, artistList.get(4)),
            new Song(6, DaoConstants.REINCARNATE, 220, artistList.get(2)),
            new Song(7, DaoConstants.THOUGHTS_PRAYERS, 240, artistList.get(2)),
            new Song(8, DaoConstants.FAINT, 162, artistList.get(5)),
            new Song(9, DaoConstants.NUMB, 185, artistList.get(5))
    ));

    private StaticLists() {
    }
}