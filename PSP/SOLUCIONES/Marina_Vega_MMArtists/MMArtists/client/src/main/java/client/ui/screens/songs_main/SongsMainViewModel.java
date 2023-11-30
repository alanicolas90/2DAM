package client.ui.screens.songs_main;

import client.domain.usecases.songs.GetAllSongsUseCase;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SongsMainViewModel {
    private final GetAllSongsUseCase getAllSongsUseCase;
    private final ObjectProperty<SongsMainState> state;

    @Inject
    public SongsMainViewModel(GetAllSongsUseCase getAllSongsUseCase) {
        this.getAllSongsUseCase = getAllSongsUseCase;
        state = new SimpleObjectProperty<>(new SongsMainState(null, null, false));
    }

    public ReadOnlyObjectProperty<SongsMainState> getState() {
        return state;
    }

    public void loadSongs() {
        state.setValue(new SongsMainState(null, null, true));

        getAllSongsUseCase.getAllSongs()
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(songs -> state.setValue(new SongsMainState(songs, null, false)))
                        .peekLeft(apiError -> state.setValue(new SongsMainState(null, apiError, false)))
                );
    }
}