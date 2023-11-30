package client.ui.screens.songs_main;

import domain.common.ApiError;
import domain.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class SongsMainState {

    private final List<Song> songList;
    private final ApiError error;
    private final boolean loading;

    public SongsMainState(List<Song> songList, ApiError error, boolean loading) {
        this.songList = songList;
        this.error = error;
        this.loading = loading;
    }
}