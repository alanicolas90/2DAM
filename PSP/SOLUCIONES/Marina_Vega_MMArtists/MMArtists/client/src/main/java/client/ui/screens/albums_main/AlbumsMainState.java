package client.ui.screens.albums_main;

import domain.common.ApiError;
import domain.model.Album;
import lombok.Data;

import java.util.List;

@Data
public class AlbumsMainState {
    private final List<Album> albumList;
    private final ApiError error;
    private final boolean loading;

    public AlbumsMainState(List<Album> albumList, ApiError error, boolean loading) {
        this.albumList = albumList;
        this.error = error;
        this.loading = loading;
    }
}