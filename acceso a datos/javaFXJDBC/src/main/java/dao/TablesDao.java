package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.Table;

import java.util.List;

public interface TablesDao {

    Either<ErrorC, List<Table>> getAll();

    Either<ErrorC, Table> get(int tableNumber);
}
