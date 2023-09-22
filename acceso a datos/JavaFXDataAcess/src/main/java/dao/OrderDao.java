package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.Order;

import java.util.List;

public interface OrderDao {
    Either<ErrorC, List<Order>> getAll();

    Either<Integer, Order> get(int id);
}
