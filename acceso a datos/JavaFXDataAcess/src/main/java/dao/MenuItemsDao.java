package dao;

import io.vavr.control.Either;
import model.ErrorC;

import java.util.List;

public interface MenuItemsDao {

    Either<ErrorC, List<String>> getAll();
}
