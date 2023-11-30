package client.ui.screens.artists_main;

import client.domain.usecases.albums.GetAlbumsByArtistUseCase;
import client.domain.usecases.artists.AddArtistUseCase;
import client.domain.usecases.artists.DeleteArtistUseCase;
import client.domain.usecases.artists.GetAllArtistsUseCase;
import client.domain.usecases.artists.UpdateArtistsUseCase;
import client.ui.screens.common.UIConstants;
import domain.common.ApiError;
import domain.model.Album;
import domain.model.Artist;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class ArtistsMainViewModel {

    private final GetAllArtistsUseCase getAllArtistsUseCase;
    private final GetAlbumsByArtistUseCase getAlbumsByArtistUseCase;
    private final AddArtistUseCase addArtistUseCase;
    private final UpdateArtistsUseCase updateArtistsUseCase;
    private final DeleteArtistUseCase deleteArtistUseCase;
    private final ObjectProperty<ArtistsMainState> state;

    @Inject
    public ArtistsMainViewModel(GetAllArtistsUseCase getAllArtistsUseCase, GetAlbumsByArtistUseCase getAlbumsByArtistUseCase, AddArtistUseCase addArtistUseCase, UpdateArtistsUseCase updateArtistsUseCase, DeleteArtistUseCase deleteArtistUseCase) {
        this.getAllArtistsUseCase = getAllArtistsUseCase;
        this.getAlbumsByArtistUseCase = getAlbumsByArtistUseCase;
        this.addArtistUseCase = addArtistUseCase;
        this.updateArtistsUseCase = updateArtistsUseCase;
        this.deleteArtistUseCase = deleteArtistUseCase;
        state = new SimpleObjectProperty<>(new ArtistsMainState(null, null, false, null, null, null, null));
    }

    public ReadOnlyObjectProperty<ArtistsMainState> getState() {
        return state;
    }

    public void loadArtists() {
        state.setValue(new ArtistsMainState(null, null, true, null, null, null, null));

        getAllArtistsUseCase.getAllArtists()
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(artists -> state.setValue(new ArtistsMainState(artists, null, false, state.get().getSelectedArtist(), null, null, null)))
                        .peekLeft(apiError -> state.setValue(new ArtistsMainState(null, apiError, false, state.get().getSelectedArtist(), null, null, null)))
                );
    }

    public void selectArtist(Artist artist) {
        state.setValue(new ArtistsMainState(state.get().getArtistList(), null, true, null, null, null, null));

        getAlbumsByArtistUseCase.getAlbumsByArtist(artist.getId())
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(albums -> state.setValue(new ArtistsMainState(state.get().getArtistList(), null, false, artist, albums, null, null)))
                        .peekLeft(apiError -> state.setValue(new ArtistsMainState(state.get().getArtistList(), apiError, false, artist, null, null, null)))
                );
    }

    public void addArtist(String name, String country, String debutYear) {
        int debutYearInt;
        try {
            debutYearInt = Integer.parseInt(debutYear);
            addArtistUseCase.addArtist(new Artist(name, country, debutYearInt))
                    .observeOn(Schedulers.single())
                    .subscribe(singleResult -> singleResult
                            .peek(artist -> {
                                List<Artist> artistList = state.get().getArtistList();
                                artistList.add(artist);
                                state.setValue(new ArtistsMainState(artistList, null, false, artist, state.get().getAlbumList(), UIConstants.THE_ARTIST_WAS_ADDED, null));
                            })
                            .peekLeft(apiError -> state.setValue(new ArtistsMainState(state.get().getArtistList(), apiError, false, state.get().getSelectedArtist(), state.get().getAlbumList(), null, null)))
                    );
        } catch (NumberFormatException e) {
            state.setValue(new ArtistsMainState(state.get().getArtistList(), new ApiError(UIConstants.THE_DEBUT_YEAR_MUST_BE_A_NUMBER), false, state.get().getSelectedArtist(), state.get().getAlbumList(), null, null));
        }

    }

    public void updateArtist(String name, String country, String debutYear) {
        int debutYearInt;
        try {
            debutYearInt = Integer.parseInt(debutYear);
            updateArtistsUseCase.updateArtist(new Artist(state.get().getSelectedArtist().getId(), name, country, debutYearInt))
                    .observeOn(Schedulers.single())
                    .subscribe(singleResult -> singleResult
                            .peek(artist -> {
                                List<Artist> artistList = state.get().getArtistList();
                                artistList.set(artistList.indexOf(artist), artist);
                                state.setValue(new ArtistsMainState(artistList, null, false, artist, state.get().getAlbumList(), UIConstants.THE_ARTIST_WAS_UPDATED, null));
                            })
                            .peekLeft(apiError -> state.setValue(new ArtistsMainState(state.get().getArtistList(), apiError, false, state.get().getSelectedArtist(), state.get().getAlbumList(), null, null)))
                    );
        } catch (NumberFormatException e) {
            state.setValue(new ArtistsMainState(state.get().getArtistList(), new ApiError(UIConstants.THE_DEBUT_YEAR_MUST_BE_A_NUMBER), false, state.get().getSelectedArtist(), state.get().getAlbumList(), null, null));
        }
    }

    public void deleteArtist() {
        state.setValue(new ArtistsMainState(state.get().getArtistList(), null, true, state.getValue().getSelectedArtist(), state.get().getAlbumList(), null, null));

        deleteArtistUseCase.deleteArtist(state.get().getSelectedArtist().getId())
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(s -> {
                            List<Artist> artistList = state.get().getArtistList();
                            artistList.remove(state.get().getSelectedArtist());
                            state.setValue(new ArtistsMainState(artistList, null, false, null, null, UIConstants.THE_ARTIST_WAS_DELETED, null));
                        })
                        .peekLeft(apiError -> state.setValue(new ArtistsMainState(state.get().getArtistList(), apiError, false, state.get().getSelectedArtist(), state.get().getAlbumList(), null, null)))
                );
    }

    public void selectAlbum(Album albumSelected) {
        state.setValue(new ArtistsMainState(state.get().getArtistList(), null, false, state.getValue().getSelectedArtist(), state.get().getAlbumList(), null, albumSelected));
    }
}