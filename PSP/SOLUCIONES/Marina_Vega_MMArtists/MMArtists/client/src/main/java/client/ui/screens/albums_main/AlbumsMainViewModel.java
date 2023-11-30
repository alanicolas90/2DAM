package client.ui.screens.albums_main;

import client.domain.usecases.albums.GetAllAlbumsUseCase;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AlbumsMainViewModel {

    private final GetAllAlbumsUseCase getAllAlbumsUseCase;
    private final ObjectProperty<AlbumsMainState> state;

    @Inject
    public AlbumsMainViewModel(GetAllAlbumsUseCase getAllAlbumsUseCase) {
        this.getAllAlbumsUseCase = getAllAlbumsUseCase;
        state = new SimpleObjectProperty<>(new AlbumsMainState(null, null, false));
    }

    public ReadOnlyObjectProperty<AlbumsMainState> getState() {
        return state;
    }

    public void loadAlbums() {
        state.setValue(new AlbumsMainState(null, null, true));

        getAllAlbumsUseCase.getAllAlbums()
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(albums -> state.setValue(new AlbumsMainState(albums, null, false)))
                        .peekLeft(apiError -> state.setValue(new AlbumsMainState(null, apiError, false)))
                );
    }
}