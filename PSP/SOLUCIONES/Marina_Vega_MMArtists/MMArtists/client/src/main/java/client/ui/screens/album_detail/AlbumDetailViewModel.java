package client.ui.screens.album_detail;

import client.common.Constants;
import client.domain.usecases.albums.DeleteAlbumUseCase;
import client.domain.usecases.songs.DeleteSongsUseCase;
import client.domain.usecases.songs.GetSongsByAlbumUseCase;
import domain.common.ApiError;
import domain.model.Album;
import domain.model.Song;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class AlbumDetailViewModel {

    private final GetSongsByAlbumUseCase getSongsByAlbumUseCase;
    private final DeleteSongsUseCase deleteSongsUseCase;

    private final DeleteAlbumUseCase deleteAlbumUseCase;
    private final ObjectProperty<AlbumDetailState> state;

    @Inject
    public AlbumDetailViewModel(GetSongsByAlbumUseCase getSongsByAlbumUseCase, DeleteSongsUseCase deleteSongsUseCase, DeleteAlbumUseCase deleteAlbumUseCase) {
        this.getSongsByAlbumUseCase = getSongsByAlbumUseCase;
        this.deleteSongsUseCase = deleteSongsUseCase;
        this.deleteAlbumUseCase = deleteAlbumUseCase;
        state = new SimpleObjectProperty<>(new AlbumDetailState(null, null, false, null, false));
    }

    public ReadOnlyObjectProperty<AlbumDetailState> getState() {
        return state;
    }

    public void loadSongs(Album albumSelected) {
        state.setValue(new AlbumDetailState(null, null, true, albumSelected, false));

        getSongsByAlbumUseCase.getSongsByAlbum(albumSelected.getId())
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(songs -> state.setValue(new AlbumDetailState(songs, null, false, albumSelected, false)))
                        .peekLeft(apiError -> state.setValue(new AlbumDetailState(null, apiError, false, albumSelected, false)))
                );
    }

    public void deleteSelectedSongs(List<Song> songsToDelete) {
        state.setValue(new AlbumDetailState(state.get().getSongsByAlbum(), null, true, state.getValue().getAlbumSelected(), false));
        if (songsToDelete.isEmpty()) {
            state.setValue(new AlbumDetailState(state.getValue().getSongsByAlbum(), new ApiError(Constants.NO_SONGS_SELECTED), false, state.getValue().getAlbumSelected(), false));
        } else {
            deleteSongsUseCase.deleteSongs(songsToDelete)
                    .observeOn(Schedulers.single())
                    .subscribe(singleResult -> singleResult
                            .peek(songs -> {
                                List<Song> newListOfSongs = state.getValue().getSongsByAlbum();
                                newListOfSongs.removeAll(songsToDelete);
                                state.setValue(new AlbumDetailState(newListOfSongs, null, false, state.getValue().getAlbumSelected(), false));
                            })
                            .peekLeft(apiError -> state.setValue(new AlbumDetailState(null, apiError, false, state.getValue().getAlbumSelected(), false)))
                    );
        }
    }

    public void deleteAlbum() {
        state.setValue(new AlbumDetailState(state.get().getSongsByAlbum(), null, true, state.getValue().getAlbumSelected(), false));
        deleteAlbumUseCase.deleteAlbum(state.getValue().getAlbumSelected().getId())
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(songs -> state.setValue(new AlbumDetailState(state.getValue().getSongsByAlbum(), null, false, state.getValue().getAlbumSelected(), true)))
                        .peekLeft(apiError -> state.setValue(new AlbumDetailState(state.getValue().getSongsByAlbum(), apiError, false, state.getValue().getAlbumSelected(), false)))
                );
    }
}