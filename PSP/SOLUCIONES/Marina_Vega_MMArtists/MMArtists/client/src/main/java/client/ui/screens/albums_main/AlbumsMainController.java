package client.ui.screens.albums_main;

import client.ui.screens.common.BaseScreenController;
import domain.model.Album;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class AlbumsMainController extends BaseScreenController {
    private final AlbumsMainViewModel albumsMainViewModel;
    @FXML
    public MFXTableView<Album> mfxTableAlbums;
    @FXML
    public MFXTableColumn<Album> colAlbumID;
    @FXML
    public MFXTableColumn<Album> colAlbumName;

    @Inject
    public AlbumsMainController(AlbumsMainViewModel albumsMainViewModel) {
        this.albumsMainViewModel = albumsMainViewModel;
    }

    @Override
    public void principalLoaded() {
        colAlbumID.setRowCellFactory(city -> new MFXTableRowCell<>(Album::getId));
        colAlbumName.setRowCellFactory(city -> new MFXTableRowCell<>(Album::getName));
        stateChanges();
        albumsMainViewModel.loadAlbums();
    }

    private void stateChanges() {
        albumsMainViewModel.getState().addListener((observableValue, oldAlbumsMainState, newAlbumsMainState) -> Platform.runLater(() -> {
            getPrincipalController().isLoading(newAlbumsMainState.isLoading());
            if (newAlbumsMainState.getError() != null) {
                getPrincipalController().showAlert(newAlbumsMainState.getError().getMessage(), Alert.AlertType.ERROR);
            }
            if (newAlbumsMainState.getAlbumList() != null) {
                mfxTableAlbums.getItems().clear();
                mfxTableAlbums.getItems().addAll(newAlbumsMainState.getAlbumList());
            }
        }));
    }
}