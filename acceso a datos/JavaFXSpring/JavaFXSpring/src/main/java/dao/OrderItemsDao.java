package dao;

import io.vavr.control.Either;
import model.ErrorC;
import model.OrderItem;

import java.util.List;

public interface OrderItemsDao {

    Either<ErrorC, List<OrderItem>> get(int orderId);
    Either<ErrorC, List<OrderItem>> getAll();
}
