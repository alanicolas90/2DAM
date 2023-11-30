package client.ui.screens.album_detail;

import client.ui.screens.common.BaseScreenController;
import client.ui.screens.common.UIConstants;
import domain.model.Song;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class AlbumDetailController extends BaseScreenController {
    private final AlbumDetailViewModel albumDetailViewModel;
    @FXML
    private Label tvTitle;
    @FXML
    private MFXTableView<Song> mfxTableSongs;
    @FXML
    private MFXTableColumn<Song> colSongID;
    @FXML
    private MFXTableColumn<Song> colSongName;
    @FXML
    private MFXTableColumn<Song> colNumberOfAlbums;

    @Inject
    public AlbumDetailController(AlbumDetailViewModel albumDetailViewModel) {
        this.albumDetailViewModel = albumDetailViewModel;
    }

    @Override
    public void principalLoaded() {
        colSongID.setRowCellFactory(song -> new MFXTableRowCell<>(Song::getId));
        colSongName.setRowCellFactory(song -> new MFXTableRowCell<>(Song::getName));
        colNumberOfAlbums.setRowCellFactory(song -> new MFXTableRowCell<>(song1 -> song1.getAlbumsThatContainThisSong().size()));
        mfxTableSongs.getSelectionModel().allowsMultipleSelection();
        stateChanges();

        albumDetailViewModel.loadSongs(getPrincipalController().getAlbumSelected());
    }


    private void stateChanges() {
        albumDetailViewModel.getState().addListener((observableValue, oldAlbumDetailState, newAlbumDetailState) -> Platform.runLater(() -> {
            getPrincipalController().isLoading(newAlbumDetailState.isLoading());
            if (newAlbumDetailState.getError() != null) {
                getPrincipalController().showAlert(newAlbumDetailState.getError().getMessage(), Alert.AlertType.ERROR);
            }
            if (newAlbumDetailState.getAlbumSelected() != null) {
                tvTitle.setText(UIConstants.ALBUM + newAlbumDetailState.getAlbumSelected().getName());
            }
            if (newAlbumDetailState.getSongsByAlbum() != null) {
                mfxTableSongs.getItems().clear();
                mfxTableSongs.getItems().addAll(newAlbumDetailState.getSongsByAlbum());
            } else {
                mfxTableSongs.getItems().clear();
            }
            if (newAlbumDetailState.isAlbumDeleted()) {
                getPrincipalController().showAlert(UIConstants.ALBUM_DELETED, Alert.AlertType.INFORMATION);
                getPrincipalController().goToArtistsMainAfterDeletingAlbum();
            }
        }));
    }

    public void deleteAlbum() {
        albumDetailViewModel.deleteAlbum();
    }

    public void deleteSelectedSongs() {
        albumDetailViewModel.deleteSelectedSongs(mfxTableSongs.getSelectionModel().getSelectedValues());
    }
}