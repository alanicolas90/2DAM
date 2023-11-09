package dao;

import dao.model.ErrorC;
import dao.model.Order;
import io.vavr.control.Either;

import java.util.List;

public interface OrdersDao {

    Either<ErrorC, List<Order>> getAll();

    Either<ErrorC, List<Order>> get(int idUserLogged);

    Either<ErrorC, Integer> add(Order order);

    Either<ErrorC, Integer> update(Order order);

    Either<ErrorC, Integer> delete(int id, boolean delete);
}
