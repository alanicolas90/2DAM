package server.dao.data;

import domain.model.Album;
import domain.model.Artist;
import domain.model.Song;
import domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class StaticLists {

    public static final List<Artist> artistList = new ArrayList<>(List.of(
            new Artist(DataConstants.ID_1, DataConstants.BAD_OMENS, DataConstants.USA, 2015),
            new Artist(DataConstants.ID_2, DataConstants.BRING_ME_THE_HORIZON, DataConstants.UK, 2004),
            new Artist(DataConstants.ID_3, DataConstants.MOTIONLESS_IN_WHITE, DataConstants.USA, 2005),
            new Artist(DataConstants.ID_4, DataConstants.BLACK_VEIL_BRIDES, DataConstants.USA, 2006),
            new Artist(DataConstants.ID_5, DataConstants.SET_IT_OFF, DataConstants.USA, 2008)
    ));

    public static final List<Song> songList = new ArrayList<>(List.of(
            //Bad Omens
            new Song(DataConstants.ID_1, DataConstants.THE_WORST_IN_ME, new ArrayList<>(List.of(DataConstants.ID_1, DataConstants.ID_2))),
            new Song(DataConstants.ID_2, DataConstants.LIMITS, new ArrayList<>(List.of(DataConstants.ID_2, DataConstants.ID_3, DataConstants.ID_4, DataConstants.ID_5))),
            new Song(DataConstants.ID_3, DataConstants.NEVER_KNOW, new ArrayList<>(List.of(DataConstants.ID_2, DataConstants.ID_6, DataConstants.ID_3, DataConstants.ID_7, DataConstants.ID_5, DataConstants.ID_8))),
            new Song(DataConstants.ID_4, DataConstants.SAID_DONE, new ArrayList<>(List.of(DataConstants.ID_5, DataConstants.ID_9))),
            //Bring Me The Horizon
            new Song(DataConstants.ID_5, DataConstants.DI_E_4_U, new ArrayList<>(List.of(DataConstants.ID_10, DataConstants.ID_11))),
            new Song(DataConstants.ID_6, DataConstants.CAN_YOU_FEEL_MY_HEART, new ArrayList<>(List.of(DataConstants.ID_12, DataConstants.ID_13, DataConstants.ID_14, DataConstants.ID_15, DataConstants.ID_16))),
            new Song(DataConstants.ID_7, DataConstants.PARASITE_EVE, new ArrayList<>(List.of(DataConstants.ID_17, DataConstants.ID_18))),
            new Song(DataConstants.ID_8, DataConstants.TEARDROPS, new ArrayList<>(List.of(DataConstants.ID_18, DataConstants.ID_19))),
            new Song(DataConstants.ID_9, DataConstants.OBEY, new ArrayList<>(List.of(DataConstants.ID_18, DataConstants.ID_20))),
            new Song(DataConstants.ID_10, DataConstants.SLEEPWALKING, new ArrayList<>(List.of(DataConstants.ID_12, DataConstants.ID_15, DataConstants.ID_21))),
            //Motionless In White
            new Song(DataConstants.ID_11, DataConstants.SIGN_OF_LIFE, new ArrayList<>(List.of(DataConstants.ID_22, DataConstants.ID_23))),
            //Black Veil Brides
            new Song(DataConstants.ID_21, DataConstants.SAVIOUR_II, new ArrayList<>(List.of(DataConstants.ID_24, DataConstants.ID_25, DataConstants.ID_26))),
            //Set It Off
            new Song(DataConstants.ID_24, DataConstants.PUNCHING_BAG, new ArrayList<>(List.of(DataConstants.ID_27, DataConstants.ID_28)))
    ));

    public static final List<Album> albumList = new ArrayList<>(List.of(
            //Bad Omens
            new Album(DataConstants.ID_1, DataConstants.BAD_OMENS, new ArrayList<>(List.of(DataConstants.ID_1)), DataConstants.ID_1),
            new Album(DataConstants.ID_2, DataConstants.LIVE, new ArrayList<>(List.of(DataConstants.ID_1, DataConstants.ID_2)), DataConstants.ID_1),
            new Album(DataConstants.ID_3, DataConstants.FGBGFM_UNPLUGGED, new ArrayList<>(List.of(DataConstants.ID_2, DataConstants.ID_3)), DataConstants.ID_1),
            new Album(DataConstants.ID_4, DataConstants.LIMITS, new ArrayList<>(List.of(DataConstants.ID_2)), DataConstants.ID_1),
            new Album(DataConstants.ID_5, DataConstants.FINDING_GOD_BEFORE_GOD_FINDS_ME_DELUXE, new ArrayList<>(List.of(DataConstants.ID_2, DataConstants.ID_3, DataConstants.ID_4)), DataConstants.ID_1),
            new Album(DataConstants.ID_6, DataConstants.NEVER_KNOW_LIVE, new ArrayList<>(List.of(DataConstants.ID_3)), DataConstants.ID_1),
            new Album(DataConstants.ID_7, DataConstants.NEVER_KNOW_UNPLUGGED, new ArrayList<>(List.of(DataConstants.ID_3)), DataConstants.ID_1),
            new Album(DataConstants.ID_8, DataConstants.NEVER_KNOW, new ArrayList<>(List.of(DataConstants.ID_3)), DataConstants.ID_1),
            new Album(DataConstants.ID_9, DataConstants.FINDING_GOD_BEFORE_GOD_FINDS_ME, new ArrayList<>(List.of(DataConstants.ID_4)), DataConstants.ID_1),
            //Bring Me The Horizon
            new Album(DataConstants.ID_10, DataConstants.DI_E_4_U, new ArrayList<>(List.of(DataConstants.ID_5)), DataConstants.ID_2),
            new Album(DataConstants.ID_11, DataConstants.DI_E_4_U_SIX_IMPALA_DI_E_6_U_REMIX, new ArrayList<>(List.of(DataConstants.ID_5)), DataConstants.ID_2),
            new Album(DataConstants.ID_12, DataConstants.SEMPITERNAL_EXPANDED_EDITION, new ArrayList<>(List.of(DataConstants.ID_6, DataConstants.ID_10)), DataConstants.ID_2),
            new Album(DataConstants.ID_13, DataConstants.CAN_YOU_FEEL_MY_HEART, new ArrayList<>(List.of(DataConstants.ID_6)), DataConstants.ID_2),
            new Album(DataConstants.ID_14, DataConstants.REC_2004_2013, new ArrayList<>(List.of(DataConstants.ID_6)), DataConstants.ID_2),
            new Album(DataConstants.ID_15, DataConstants.LIVE_AT_THE_ROYAL_ALBERT_HALL, new ArrayList<>(List.of(DataConstants.ID_6, DataConstants.ID_10)), DataConstants.ID_2),
            new Album(DataConstants.ID_16, DataConstants.CAN_YOU_FEEL_MY_HEART_REMIX, new ArrayList<>(List.of(DataConstants.ID_6)), DataConstants.ID_2),
            new Album(DataConstants.ID_17, DataConstants.PARASITE_EVE, new ArrayList<>(List.of(DataConstants.ID_7)), DataConstants.ID_2),
            new Album(DataConstants.ID_18, DataConstants.POST_HUMAN_SURVIVAL_HORROR, new ArrayList<>(List.of(DataConstants.ID_7, DataConstants.ID_8, DataConstants.ID_9)), DataConstants.ID_2),
            new Album(DataConstants.ID_19, DataConstants.TEARDROPS, new ArrayList<>(List.of(DataConstants.ID_8)), DataConstants.ID_2),
            new Album(DataConstants.ID_20, DataConstants.OBEY, new ArrayList<>(List.of(DataConstants.ID_9)), DataConstants.ID_2),
            new Album(DataConstants.ID_21, DataConstants.SLEEPWALKING, new ArrayList<>(List.of(DataConstants.ID_10)), DataConstants.ID_2),
            //Motionless In White
            new Album(DataConstants.ID_22, DataConstants.SCORING_THE_END_OF_THE_WORLD, new ArrayList<>(List.of(DataConstants.ID_11)), DataConstants.ID_3),
            new Album(DataConstants.ID_23, DataConstants.SCORING_THE_END_OF_THE_WORLD_DELUXE_EDITION, new ArrayList<>(List.of(DataConstants.ID_11)), DataConstants.ID_3),
            //Black Veil Brides
            new Album(DataConstants.ID_24, DataConstants.SAVIOUR_II, new ArrayList<>(List.of(DataConstants.ID_21)), DataConstants.ID_4),
            new Album(DataConstants.ID_25, DataConstants.THE_MOURNING, new ArrayList<>(List.of(DataConstants.ID_21)), DataConstants.ID_4),
            new Album(DataConstants.ID_26, DataConstants.SAVIOUR_II_ORCHESTRAL_VERSION, new ArrayList<>(List.of(DataConstants.ID_21)), DataConstants.ID_4),
            //Set It Off
            new Album(DataConstants.ID_27, DataConstants.PUNCHING_BAG, new ArrayList<>(List.of(DataConstants.ID_24)), DataConstants.ID_5),
            new Album(DataConstants.ID_28, DataConstants.PUNCHING_BAG_ACOUSTIC, new ArrayList<>(List.of(DataConstants.ID_24)), DataConstants.ID_5)
    ));

    public static final List<User> userList = new ArrayList<>(List.of(
            new User(DataConstants.MARINA, DataConstants.MARINA_HASHED_PASSWORD),
            new User(DataConstants.OSCAR, DataConstants.OSCAR_HASHED_PASSWORD)
    ));

    private StaticLists() {
    }
}