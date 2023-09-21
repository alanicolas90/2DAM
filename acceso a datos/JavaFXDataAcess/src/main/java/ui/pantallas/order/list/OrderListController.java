package ui.pantallas.order.list;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Order;
import ui.pantallas.common.BasePantallaController;

import java.security.Timestamp;

public class OrderListController extends BasePantallaController {

    @FXML
    private TableColumn<Order, Integer> columnId;
    @FXML
    private TableColumn<Order, Timestamp> columnDate;
    @FXML
    private TableColumn<Order, Integer> columnCustomerId;
    @FXML
    private TableColumn<Order, Integer> columnTableNumber;
    @FXML
    private TableView<Order> tableOrders;


}

