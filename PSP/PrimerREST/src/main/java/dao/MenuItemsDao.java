package dao;

import dao.model.ErrorC;
import dao.model.MenuItem;
import io.vavr.control.Either;


import java.util.List;

public interface MenuItemsDao {

    Either<ErrorC, List<MenuItem>> getAll();
}
