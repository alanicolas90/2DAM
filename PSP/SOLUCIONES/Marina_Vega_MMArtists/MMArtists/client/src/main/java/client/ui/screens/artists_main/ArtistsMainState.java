package client.ui.screens.artists_main;

import domain.common.ApiError;
import domain.model.Album;
import domain.model.Artist;
import lombok.Data;

import java.util.List;

@Data
public class ArtistsMainState {

    private final List<Artist> artistList;
    private final ApiError error;
    private final boolean loading;
    private final Artist selectedArtist;
    private final List<Album> albumList;
    private final String confirmationMessage;
    private final Album albumSelected;

    public ArtistsMainState(List<Artist> artistList, ApiError error, boolean loading, Artist selectedArtist, List<Album> albumList, String confirmationMessage, Album albumSelected) {
        this.artistList = artistList;
        this.error = error;
        this.loading = loading;
        this.selectedArtist = selectedArtist;
        this.albumList = albumList;
        this.confirmationMessage = confirmationMessage;
        this.albumSelected = albumSelected;
    }
}