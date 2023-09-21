package ui.pantallas.order.add;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Order;
import ui.pantallas.common.BasePantallaController;

import java.security.Timestamp;

public class OrderAddController extends BasePantallaController {

    @FXML
    private TableView<Order> tableOrders;
    @FXML
    private TableColumn<Order, Integer> columnId;
    @FXML
    private TableColumn<Order, Timestamp> columnDate;
    @FXML
    private TableColumn<Order, Integer> columnCustomerId;
    @FXML
    private TableColumn<Order, Integer> columnTableNumber;


    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtCustomerId;
    @FXML
    private TextField txtTableNumber;


}
