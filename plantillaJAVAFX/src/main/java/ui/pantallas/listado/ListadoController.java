package ui.pantallas.listado;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.pantallas.common.BasePantallaController;


public class ListadoController extends BasePantallaController {


    private final ListadoViewModel viewModel;
    public MFXButton ver;
    @FXML
    private TableColumn<String, String> colCol;
    @FXML
    private TableColumn<String, String> colDes;
    @FXML
    private TableColumn<String, Integer> colNum;

    @FXML
    private TableView<String> tablaNormal;
    @FXML
    private MFXTextField txtNombre;
    @FXML
    private MFXTableView<String> tablaMaterial;
    @Inject
    public ListadoController(ListadoViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void initialize() {
// tabla materialFX

    }

}
