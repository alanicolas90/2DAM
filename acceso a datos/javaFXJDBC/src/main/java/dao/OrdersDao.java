package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersDao {

    Either<ErrorC, List<Order>> getAll();

    Either<ErrorC, List<Order>> get(int idUserLogged);

    Either<ErrorC,Integer> add(LocalDateTime date, int customerId, int tableNumber);

    Either<ErrorC, Integer> update(LocalDateTime date, int tableNumber, int orderID);

    Either<ErrorC, Integer> delete(int id);
}
