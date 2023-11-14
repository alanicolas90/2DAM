package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.MenuItem;
import ui.screens.order.model.MenuItemTable;

import java.util.List;

public interface MenuItemsDao {

    Either<ErrorC, List<MenuItem>> getAll();

    Either<ErrorC, List<MenuItemTable>> get(int idOrder);

    Either<ErrorC,MenuItem> get(String nameMenuItem);
}
