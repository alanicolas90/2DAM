package client.ui.screens.songs_main;

import client.ui.screens.common.BaseScreenController;
import domain.model.Song;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class SongsMainController extends BaseScreenController {
    private final SongsMainViewModel songsMainViewModel;

    @FXML
    private MFXTableView<Song> mfxTableSongs;
    @FXML
    private MFXTableColumn<Song> colSongID;
    @FXML
    private MFXTableColumn<Song> colSongName;
    @FXML
    private MFXTableColumn<Song> colNumberOfAlbums;

    @Inject
    public SongsMainController(SongsMainViewModel songsMainViewModel) {
        this.songsMainViewModel = songsMainViewModel;
    }

    @Override
    public void principalLoaded() {
        colSongID.setRowCellFactory(song -> new MFXTableRowCell<>(Song::getId));
        colSongName.setRowCellFactory(song -> new MFXTableRowCell<>(Song::getName));
        colNumberOfAlbums.setRowCellFactory(song -> new MFXTableRowCell<>(song1 -> song1.getAlbumsThatContainThisSong().size()));
        stateChanges();
        songsMainViewModel.loadSongs();
    }

    private void stateChanges() {
        songsMainViewModel.getState().addListener((observableValue, oldSongsMainState, newSongsMainState) -> Platform.runLater(() -> {
            getPrincipalController().isLoading(newSongsMainState.isLoading());
            if (newSongsMainState.getError() != null) {
                getPrincipalController().showAlert(newSongsMainState.getError().getMessage(), Alert.AlertType.ERROR);
            }
            if (newSongsMainState.getSongList() != null) {
                mfxTableSongs.getItems().clear();
                mfxTableSongs.getItems().addAll(newSongsMainState.getSongList());
            }
        }));
    }
}