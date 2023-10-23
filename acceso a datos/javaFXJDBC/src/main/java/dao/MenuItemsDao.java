package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.MenuItem;

import java.util.List;

public interface MenuItemsDao {

    Either<ErrorC, List<MenuItem>> getAll();
}
