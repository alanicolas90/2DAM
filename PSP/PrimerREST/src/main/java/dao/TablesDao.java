package dao;

import dao.model.ErrorC;
import dao.model.Table;
import io.vavr.control.Either;

import java.util.List;

public interface TablesDao {

    Either<ErrorC, List<Table>> getAll();

    Either<ErrorC, Table> get(int tableNumber);
}
