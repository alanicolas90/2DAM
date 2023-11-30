package client.ui.screens.album_detail;

import domain.common.ApiError;
import domain.model.Album;
import domain.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class AlbumDetailState {
    private final List<Song> songsByAlbum;
    private final ApiError error;
    private final boolean loading;
    private final Album albumSelected;
    private final boolean isAlbumDeleted;

    public AlbumDetailState(List<Song> songsByAlbum, ApiError error, boolean loading, Album albumSelected, boolean isAlbumDeleted) {
        this.songsByAlbum = songsByAlbum;
        this.error = error;
        this.loading = loading;
        this.albumSelected = albumSelected;
        this.isAlbumDeleted = isAlbumDeleted;
    }
}