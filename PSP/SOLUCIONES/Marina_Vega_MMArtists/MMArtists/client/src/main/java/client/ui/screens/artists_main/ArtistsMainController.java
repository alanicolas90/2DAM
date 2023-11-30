package client.ui.screens.artists_main;

import client.ui.screens.common.BaseScreenController;
import domain.model.Album;
import domain.model.Artist;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class ArtistsMainController extends BaseScreenController {

    private final ArtistsMainViewModel artistsMainViewModel;
    @FXML
    private MFXTextField tvName;
    @FXML
    private MFXTextField tvDebutYear;
    @FXML
    private MFXTextField tvCountry;
    @FXML
    private MFXTableView<Artist> mfxTableArtists;
    @FXML
    private MFXTableColumn<Artist> colArtistID;
    @FXML
    private MFXTableColumn<Artist> colArtistName;
    @FXML
    private MFXTableColumn<Artist> colArtistCountry;
    @FXML
    private MFXTableColumn<Artist> colArtistDebutYear;
    @FXML
    private ComboBox<Album> cmbAlbumsByArtist;

    @Inject
    public ArtistsMainController(ArtistsMainViewModel artistsMainViewModel) {
        this.artistsMainViewModel = artistsMainViewModel;
    }

    @Override
    public void principalLoaded() {
        colArtistID.setRowCellFactory(country -> new MFXTableRowCell<>(Artist::getId));
        colArtistName.setRowCellFactory(country -> new MFXTableRowCell<>(Artist::getName));
        colArtistCountry.setRowCellFactory(country -> new MFXTableRowCell<>(Artist::getCountry));
        colArtistDebutYear.setRowCellFactory(country -> new MFXTableRowCell<>(Artist::getDebutYear));
        mfxTableArtists.getSelectionModel().selectionProperty().addListener((observableValue, oldItem, itemSelected) -> {
            if (itemSelected != null && !itemSelected.values().isEmpty()) {
                artistsMainViewModel.selectArtist(itemSelected.values().stream().findFirst().orElse(null));
            }
        });
        cmbAlbumsByArtist.getSelectionModel().selectedItemProperty().addListener((observableValue, oldItem, itemSelected) -> {
            if (itemSelected != null) {
                artistsMainViewModel.selectAlbum(itemSelected);
            }
        });
        stateChanges();
        artistsMainViewModel.loadArtists();
    }

    private void stateChanges() {
        artistsMainViewModel.getState().addListener((observableValue, oldArtistsMainState, newArtistsMainState) -> Platform.runLater(() -> {
            getPrincipalController().isLoading(newArtistsMainState.isLoading());
            if (newArtistsMainState.getArtistList() != null) {
                mfxTableArtists.getItems().clear();
                mfxTableArtists.getItems().addAll(newArtistsMainState.getArtistList());
            }
            if (newArtistsMainState.getError() != null) {
                getPrincipalController().showAlert(newArtistsMainState.getError().getMessage(), Alert.AlertType.ERROR);
            }
            selectedArtistChanges(newArtistsMainState);
            albumListChanges(newArtistsMainState);
            if (newArtistsMainState.getConfirmationMessage() != null) {
                getPrincipalController().showAlert(newArtistsMainState.getConfirmationMessage(), Alert.AlertType.INFORMATION);
            }
            if (newArtistsMainState.getAlbumSelected() != null) {
                getPrincipalController().onAlbumClicked(newArtistsMainState.getAlbumSelected());
            }
        }));
    }

    private void albumListChanges(ArtistsMainState newArtistsMainState) {
        if (newArtistsMainState.getAlbumList() != null) {
            cmbAlbumsByArtist.getItems().clear();
            cmbAlbumsByArtist.getItems().addAll(newArtistsMainState.getAlbumList());
        } else {
            cmbAlbumsByArtist.getItems().clear();
        }
    }

    private void selectedArtistChanges(ArtistsMainState newArtistsMainState) {
        if (newArtistsMainState.getSelectedArtist() != null) {
            tvName.setText(newArtistsMainState.getSelectedArtist().getName());
            tvCountry.setText(newArtistsMainState.getSelectedArtist().getCountry());
            tvDebutYear.setText(newArtistsMainState.getSelectedArtist().getDebutYear().toString());
        } else {
            tvName.clear();
            tvCountry.clear();
            tvDebutYear.clear();
        }
    }

    public void addArtist() {
        artistsMainViewModel.addArtist(tvName.getText(), tvCountry.getText(), tvDebutYear.getText());
    }

    public void updateArtist() {
        artistsMainViewModel.updateArtist(tvName.getText(), tvCountry.getText(), tvDebutYear.getText());
    }

    public void deleteArtist() {
        artistsMainViewModel.deleteArtist();
    }
}