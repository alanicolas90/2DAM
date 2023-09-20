package dao;

import io.vavr.control.Either;
import model.Order;

import java.util.List;

public interface OrderDao {
    Either<Integer, List<Order>> getAll();

    Either<Integer, Order> get(int id);
}
