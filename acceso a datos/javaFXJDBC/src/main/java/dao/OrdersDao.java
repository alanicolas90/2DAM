package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersDao {

    Either<ErrorC, List<Order>> getAll();

    Either<ErrorC, List<Order>> get(int idUserLogged);

    Either<ErrorC, Integer> add(Order order);

    Either<ErrorC, Integer> update(Order order);

    Either<ErrorC, Integer> delete(int id, boolean delete);
}
